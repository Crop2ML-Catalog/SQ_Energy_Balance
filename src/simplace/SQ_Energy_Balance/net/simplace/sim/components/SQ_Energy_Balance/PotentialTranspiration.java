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


public class PotentialTranspiration extends FWSimComponent
{
    private FWSimVariable<Double> evapoTranspiration;
    private FWSimVariable<Double> tau;
    private FWSimVariable<Double> potentialTranspiration;

    public PotentialTranspiration(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public PotentialTranspiration(){
        super();
    }
    @Override
    protected void process()
    {
        double t_evapoTranspiration = evapoTranspiration.getValue();
        double t_tau = tau.getValue();
        double t_potentialTranspiration;
        t_potentialTranspiration = t_evapoTranspiration * (1.0d - t_tau);
        potentialTranspiration.setValue(t_potentialTranspiration, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(0.9983, iFieldMap.get("PotentialTranspiration.tau"), this);
            FWSimVariable.setValue(830.958, iFieldMap.get("PotentialTranspiration.evapoTranspiration"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(1.413, iFieldMap.get("PotentialTranspiration.potentialTranspiration"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new PotentialTranspiration(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("evapoTranspiration", "evapoTranspiration", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, 830.958, this));
        addVariable(FWSimVariable.createSimVariable("tau", "plant cover factor", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 1, 0.9983, this));
        addVariable(FWSimVariable.createSimVariable("potentialTranspiration", "potential Transpiration ", DATA_TYPE.DOUBLE, CONTENT_TYPE.rate,"", 0, 10000, null, this));

        return iFieldMap;
    }
}