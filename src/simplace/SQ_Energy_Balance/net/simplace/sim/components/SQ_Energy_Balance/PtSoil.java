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


public class PtSoil extends FWSimComponent
{
    private FWSimVariable<Double> evapoTranspirationPriestlyTaylor;
    private FWSimVariable<Double> Alpha;
    private FWSimVariable<Double> tau;
    private FWSimVariable<Double> tauAlpha;
    private FWSimVariable<Double> energyLimitedEvaporation;

    public PtSoil(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public PtSoil(){
        super();
    }
    @Override
    protected void process()
    {
        double t_evapoTranspirationPriestlyTaylor = evapoTranspirationPriestlyTaylor.getValue();
        double t_Alpha = Alpha.getValue();
        double t_tau = tau.getValue();
        double t_tauAlpha = tauAlpha.getValue();
        double t_energyLimitedEvaporation;
        double AlphaE;
        if (t_tau < t_tauAlpha)
        {
            AlphaE = 1.0d;
        }
        else
        {
            AlphaE = t_Alpha - ((t_Alpha - 1.0d) * (1.0d - t_tau) / (1.0d - t_tauAlpha));
        }
        t_energyLimitedEvaporation = t_evapoTranspirationPriestlyTaylor / t_Alpha * AlphaE * t_tau;
        energyLimitedEvaporation.setValue(t_energyLimitedEvaporation, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(0.9983, iFieldMap.get("PtSoil.tau"), this);
            FWSimVariable.setValue(449.367, iFieldMap.get("PtSoil.evapoTranspirationPriestlyTaylor"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(448.240, iFieldMap.get("PtSoil.energyLimitedEvaporation"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new PtSoil(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("evapoTranspirationPriestlyTaylor", "evapoTranspiration Priestly Taylor", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 1000, 120, this));
        addVariable(FWSimVariable.createSimVariable("Alpha", "Priestley-Taylor evapotranspiration proportionality constant", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 100, 1.5, this));
        addVariable(FWSimVariable.createSimVariable("tau", "plant cover factor", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 100, 0.9983, this));
        addVariable(FWSimVariable.createSimVariable("tauAlpha", "Fraction of the total net radiation exchanged at the soil surface when AlpaE = 1", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.3, this));
        addVariable(FWSimVariable.createSimVariable("energyLimitedEvaporation", "energy Limited Evaporation ", DATA_TYPE.DOUBLE, CONTENT_TYPE.out,"", 0, 5000, null, this));

        return iFieldMap;
    }
}