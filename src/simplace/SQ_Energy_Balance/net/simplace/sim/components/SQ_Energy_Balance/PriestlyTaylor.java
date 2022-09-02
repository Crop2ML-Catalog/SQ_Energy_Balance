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


public class PriestlyTaylor extends FWSimComponent
{
    private FWSimVariable<Double> netRadiationEquivalentEvaporation;
    private FWSimVariable<Double> hslope;
    private FWSimVariable<Double> psychrometricConstant;
    private FWSimVariable<Double> Alpha;
    private FWSimVariable<Double> evapoTranspirationPriestlyTaylor;

    public PriestlyTaylor(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public PriestlyTaylor(){
        super();
    }
    @Override
    protected void process()
    {
        double t_netRadiationEquivalentEvaporation = netRadiationEquivalentEvaporation.getValue();
        double t_hslope = hslope.getValue();
        double t_psychrometricConstant = psychrometricConstant.getValue();
        double t_Alpha = Alpha.getValue();
        double t_evapoTranspirationPriestlyTaylor;
        t_evapoTranspirationPriestlyTaylor = Math.max(t_Alpha * t_hslope * t_netRadiationEquivalentEvaporation / (t_hslope + t_psychrometricConstant), 0.0d);
        evapoTranspirationPriestlyTaylor.setValue(t_evapoTranspirationPriestlyTaylor, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(1.5, iFieldMap.get("PriestlyTaylor.Alpha"), this);
            FWSimVariable.setValue(638.142, iFieldMap.get("PriestlyTaylor.netRadiationEquivalentEvaporation"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(449.367, iFieldMap.get("PriestlyTaylor.evapoTranspirationPriestlyTaylor"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new PriestlyTaylor(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("netRadiationEquivalentEvaporation", "net Radiation in Equivalent Evaporation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 5000, 638.142, this));
        addVariable(FWSimVariable.createSimVariable("hslope", "the slope of saturated vapor pressure temperature curve at a given temperature ", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 1000, 0.584, this));
        addVariable(FWSimVariable.createSimVariable("psychrometricConstant", "psychrometric constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.66, this));
        addVariable(FWSimVariable.createSimVariable("Alpha", "Priestley-Taylor evapotranspiration proportionality constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 100, 1.5, this));
        addVariable(FWSimVariable.createSimVariable("evapoTranspirationPriestlyTaylor", "evapoTranspiration of Priestly Taylor ", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, null, this));

        return iFieldMap;
    }
}