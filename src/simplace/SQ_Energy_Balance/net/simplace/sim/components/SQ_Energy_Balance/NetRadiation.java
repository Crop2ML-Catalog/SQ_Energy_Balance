package net.simplace.sim.components.SQ_Energy_Balance;
import  java.io.*;
import  java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import net.simplace.sim.model.FWSimComponent;
import net.simplace.sim.util.FWSimVarMap;
import net.simplace.sim.util.FWSimVariable;
import net.simplace.sim.util.FWSimVariable.CONTENT_TYPE;
import net.simplace.sim.util.FWSimVariable.DATA_TYPE;
import org.jdom2.Element;


public class NetRadiation extends FWSimComponent
{
    private FWSimVariable<Double> minTair;
    private FWSimVariable<Double> maxTair;
    private FWSimVariable<Double> albedoCoefficient;
    private FWSimVariable<Double> stefanBoltzman;
    private FWSimVariable<Double> elevation;
    private FWSimVariable<Double> solarRadiation;
    private FWSimVariable<Double> vaporPressure;
    private FWSimVariable<Double> extraSolarRadiation;
    private FWSimVariable<Double> netRadiation;
    private FWSimVariable<Double> netOutGoingLongWaveRadiation;

    public NetRadiation(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public NetRadiation(){
        super();
    }
    @Override
    protected void process()
    {
        double t_minTair = minTair.getValue();
        double t_maxTair = maxTair.getValue();
        double t_albedoCoefficient = albedoCoefficient.getValue();
        double t_stefanBoltzman = stefanBoltzman.getValue();
        double t_elevation = elevation.getValue();
        double t_solarRadiation = solarRadiation.getValue();
        double t_vaporPressure = vaporPressure.getValue();
        double t_extraSolarRadiation = extraSolarRadiation.getValue();
        double t_netRadiation;
        double t_netOutGoingLongWaveRadiation;
        double Nsr;
        double clearSkySolarRadiation;
        double averageT;
        double surfaceEmissivity;
        double cloudCoverFactor;
        double Nolr;
        Nsr = (1.0d - t_albedoCoefficient) * t_solarRadiation;
        clearSkySolarRadiation = (0.75d + (2 * Math.pow(10.0d, -5) * t_elevation)) * t_extraSolarRadiation;
        averageT = (Math.pow(t_maxTair + 273.16d, 4) + Math.pow(t_minTair + 273.16d, 4)) / 2.0d;
        surfaceEmissivity = 0.34d - (0.14d * Math.sqrt(t_vaporPressure / 10.0d));
        cloudCoverFactor = 1.35d * (t_solarRadiation / clearSkySolarRadiation) - 0.35d;
        Nolr = t_stefanBoltzman * averageT * surfaceEmissivity * cloudCoverFactor;
        t_netRadiation = Nsr - Nolr;
        t_netOutGoingLongWaveRadiation = Nolr;
        netRadiation.setValue(t_netRadiation, this);
        netOutGoingLongWaveRadiation.setValue(t_netOutGoingLongWaveRadiation, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(0, iFieldMap.get("NetRadiation.elevation"), this);
            FWSimVariable.setValue(3, iFieldMap.get("NetRadiation.solarRadiation"), this);
            FWSimVariable.setValue(6.1, iFieldMap.get("NetRadiation.vaporPressure"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(1.566, iFieldMap.get("NetRadiation.netRadiation"), this);
            FWSimVariable.setValue(0.744, iFieldMap.get("NetRadiation.netOutGoingLongWaveRadiation"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new NetRadiation(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("minTair", "minimum air temperature", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", -30, 45, 0.7, this));
        addVariable(FWSimVariable.createSimVariable("maxTair", "maximum air Temperature", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", -30, 45, 7.2, this));
        addVariable(FWSimVariable.createSimVariable("albedoCoefficient", "albedo Coefficient", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.23, this));
        addVariable(FWSimVariable.createSimVariable("stefanBoltzman", "stefan Boltzman constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 4.903E-09, this));
        addVariable(FWSimVariable.createSimVariable("elevation", "elevation", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", -500, 10000, 0, this));
        addVariable(FWSimVariable.createSimVariable("solarRadiation", "solar Radiation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 3, this));
        addVariable(FWSimVariable.createSimVariable("vaporPressure", "vapor Pressure", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 6.1, this));
        addVariable(FWSimVariable.createSimVariable("extraSolarRadiation", "extra Solar Radiation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 11.7, this));
        addVariable(FWSimVariable.createSimVariable("netRadiation", " net radiation ", DATA_TYPE.DOUBLE, CONTENT_TYPE.out,"", 0, 5000, null, this));
        addVariable(FWSimVariable.createSimVariable("netOutGoingLongWaveRadiation", "net OutGoing Long Wave Radiation ", DATA_TYPE.DOUBLE, CONTENT_TYPE.out,"", 0, 5000, null, this));

        return iFieldMap;
    }
}