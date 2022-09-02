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


public class Penman extends FWSimComponent
{
    private FWSimVariable<Double> evapoTranspirationPriestlyTaylor;
    private FWSimVariable<Double> hslope;
    private FWSimVariable<Double> VPDair;
    private FWSimVariable<Double> psychrometricConstant;
    private FWSimVariable<Double> Alpha;
    private FWSimVariable<Double> lambdaV;
    private FWSimVariable<Double> rhoDensityAir;
    private FWSimVariable<Double> specificHeatCapacityAir;
    private FWSimVariable<Double> conductance;
    private FWSimVariable<Double> evapoTranspirationPenman;

    public Penman(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public Penman(){
        super();
    }
    @Override
    protected void process()
    {
        double t_evapoTranspirationPriestlyTaylor = evapoTranspirationPriestlyTaylor.getValue();
        double t_hslope = hslope.getValue();
        double t_VPDair = VPDair.getValue();
        double t_psychrometricConstant = psychrometricConstant.getValue();
        double t_Alpha = Alpha.getValue();
        double t_lambdaV = lambdaV.getValue();
        double t_rhoDensityAir = rhoDensityAir.getValue();
        double t_specificHeatCapacityAir = specificHeatCapacityAir.getValue();
        double t_conductance = conductance.getValue();
        double t_evapoTranspirationPenman;
        t_evapoTranspirationPenman = t_evapoTranspirationPriestlyTaylor / t_Alpha + (1000.0d * (t_rhoDensityAir * t_specificHeatCapacityAir * t_VPDair * t_conductance / (t_lambdaV * (t_hslope + t_psychrometricConstant))));
        evapoTranspirationPenman.setValue(t_evapoTranspirationPenman, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(1.5, iFieldMap.get("Penman.Alpha"), this);
            FWSimVariable.setValue(2.454, iFieldMap.get("Penman.lambdaV"), this);
            FWSimVariable.setValue(449.367, iFieldMap.get("Penman.evapoTranspirationPriestlyTaylor"), this);
            FWSimVariable.setValue(0.584, iFieldMap.get("Penman.hslope"), this);
            FWSimVariable.setValue(2.19, iFieldMap.get("Penman.VPDair"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(830.958, iFieldMap.get("Penman.evapoTranspirationPenman"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new Penman(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("evapoTranspirationPriestlyTaylor", "evapoTranspiration of Priestly Taylor ", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, 449.367, this));
        addVariable(FWSimVariable.createSimVariable("hslope", "the slope of saturated vapor pressure temperature curve at a given temperature ", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 0.584, this));
        addVariable(FWSimVariable.createSimVariable("VPDair", " vapour pressure density", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 2.19, this));
        addVariable(FWSimVariable.createSimVariable("psychrometricConstant", "psychrometric constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.66, this));
        addVariable(FWSimVariable.createSimVariable("Alpha", "Priestley-Taylor evapotranspiration proportionality constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 100, 1.5, this));
        addVariable(FWSimVariable.createSimVariable("lambdaV", "latent heat of vaporization of water", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 10, 2.454, this));
        addVariable(FWSimVariable.createSimVariable("rhoDensityAir", "Density of air", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", null, null, 1.225, this));
        addVariable(FWSimVariable.createSimVariable("specificHeatCapacityAir", "Specific heat capacity of dry air", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.00101, this));
        addVariable(FWSimVariable.createSimVariable("conductance", "conductance", DATA_TYPE.DOUBLE, CONTENT_TYPE.state,"", 0, 10000, 598.685, this));
        addVariable(FWSimVariable.createSimVariable("evapoTranspirationPenman", " evapoTranspiration of Penman Monteith", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 5000, null, this));

        return iFieldMap;
    }
}