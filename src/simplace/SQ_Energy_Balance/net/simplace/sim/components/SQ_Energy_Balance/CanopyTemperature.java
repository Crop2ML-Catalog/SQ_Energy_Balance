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


public class CanopyTemperature extends FWSimComponent
{
    private FWSimVariable<Double> minTair;
    private FWSimVariable<Double> maxTair;
    private FWSimVariable<Double> cropHeatFlux;
    private FWSimVariable<Double> conductance;
    private FWSimVariable<Double> lambdaV;
    private FWSimVariable<Double> rhoDensityAir;
    private FWSimVariable<Double> specificHeatCapacityAir;
    private FWSimVariable<Double> minCanopyTemperature;
    private FWSimVariable<Double> maxCanopyTemperature;

    public CanopyTemperature(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public CanopyTemperature(){
        super();
    }
    @Override
    protected void process()
    {
        double t_minTair = minTair.getValue();
        double t_maxTair = maxTair.getValue();
        double t_cropHeatFlux = cropHeatFlux.getValue();
        double t_conductance = conductance.getValue();
        double t_lambdaV = lambdaV.getValue();
        double t_rhoDensityAir = rhoDensityAir.getValue();
        double t_specificHeatCapacityAir = specificHeatCapacityAir.getValue();
        double t_minCanopyTemperature;
        double t_maxCanopyTemperature;
        t_minCanopyTemperature = t_minTair + (t_cropHeatFlux / (t_rhoDensityAir * t_specificHeatCapacityAir * t_conductance / t_lambdaV * 1000.0d));
        t_maxCanopyTemperature = t_maxTair + (t_cropHeatFlux / (t_rhoDensityAir * t_specificHeatCapacityAir * t_conductance / t_lambdaV * 1000.0d));
        minCanopyTemperature.setValue(t_minCanopyTemperature, this);
        maxCanopyTemperature.setValue(t_maxCanopyTemperature, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(1.225, iFieldMap.get("CanopyTemperature.rhoDensityAir"), this);
            FWSimVariable.setValue(0.7, iFieldMap.get("CanopyTemperature.minTair"), this);
            FWSimVariable.setValue(7.2, iFieldMap.get("CanopyTemperature.maxTair"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(2.184, iFieldMap.get("CanopyTemperature.minCanopyTemperature"), this);
            FWSimVariable.setValue(8.684, iFieldMap.get("CanopyTemperature.maxCanopyTemperature"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new CanopyTemperature(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("minTair", "minimum air temperature", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", -30, 45, 0.7, this));
        addVariable(FWSimVariable.createSimVariable("maxTair", "maximum air Temperature", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", -30, 45, 7.2, this));
        addVariable(FWSimVariable.createSimVariable("cropHeatFlux", "Crop heat flux", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, 447.912, this));
        addVariable(FWSimVariable.createSimVariable("conductance", "the boundary layer conductance", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", 0, 10000, 598.685, this));
        addVariable(FWSimVariable.createSimVariable("lambdaV", "latent heat of vaporization of water", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 10, 2.454, this));
        addVariable(FWSimVariable.createSimVariable("rhoDensityAir", "Density of air", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", null, null, 1.225, this));
        addVariable(FWSimVariable.createSimVariable("specificHeatCapacityAir", "Specific heat capacity of dry air", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", null, null, 0.00101, this));
        addVariable(FWSimVariable.createSimVariable("minCanopyTemperature", "minimal Canopy Temperature  ", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", -30, 45, null, this));
        addVariable(FWSimVariable.createSimVariable("maxCanopyTemperature", "maximal Canopy Temperature ", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", -30, 45, null, this));

        return iFieldMap;
    }
}