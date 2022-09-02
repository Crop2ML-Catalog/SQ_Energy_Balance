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


public class NetRadiationEquivalentEvaporation extends FWSimComponent
{
    private FWSimVariable<Double> lambdaV;
    private FWSimVariable<Double> netRadiation;
    private FWSimVariable<Double> netRadiationEquivalentEvaporation;

    public NetRadiationEquivalentEvaporation(String aName, HashMap<String, FWSimVariable<?>> aFieldMap, HashMap<String, String> aInputMap, Element aSimComponentElement, FWSimVarMap aVarMap, int aOrderNumber)
    {
        super(aName, aFieldMap, aInputMap, aSimComponentElement, aVarMap, aOrderNumber);
    }

    public NetRadiationEquivalentEvaporation(){
        super();
    }
    @Override
    protected void process()
    {
        double t_lambdaV = lambdaV.getValue();
        double t_netRadiation = netRadiation.getValue();
        double t_netRadiationEquivalentEvaporation;
        t_netRadiationEquivalentEvaporation = t_netRadiation / t_lambdaV * 1000.0d;
        netRadiationEquivalentEvaporation.setValue(t_netRadiationEquivalentEvaporation, this);
    }

    @Override
    protected void init()
    {
    }
    public HashMap<String, FWSimVariable<?>> fillTestVariables(int aParamIndex, TEST_STATE aDefineOrCheck)
    {
        if(aParamIndex == 0 && aDefineOrCheck == TEST_STATE.DEFINE) //before process
        {
            FWSimVariable.setValue(1.566, iFieldMap.get("NetRadiationEquivalentEvaporation.netRadiation"), this);
        }
        else if(aParamIndex ==  0 && aDefineOrCheck == TEST_STATE.CHECK) //after process
        {
            FWSimVariable.setValue(638.142, iFieldMap.get("NetRadiationEquivalentEvaporation.netRadiationEquivalentEvaporation"), this);
        }
        else return null;
        return iFieldMap;
    }

    @Override
    protected FWSimComponent clone(FWSimVarMap aVarMap)
    {
        return new NetRadiationEquivalentEvaporation(iName, iFieldMap, iInputMap, iSimComponentElement, aVarMap, iOrderNumber);
    }

    @Override
    public HashMap<String, FWSimVariable<?>> createVariables()
    {
        addVariable(FWSimVariable.createSimVariable("lambdaV", "latent heat of vaporization of water", DATA_TYPE.DOUBLE, CONTENT_TYPE.constant,"", 0, 10, 2.454, this));
        addVariable(FWSimVariable.createSimVariable("netRadiation", "net radiation", DATA_TYPE.DOUBLE, CONTENT_TYPE.input,"", 0, 5000, 1.566, this));
        addVariable(FWSimVariable.createSimVariable("netRadiationEquivalentEvaporation", "net Radiation in Equivalent Evaporation ", DATA_TYPE.DOUBLE, CONTENT_TYPE.out,"", 0, 5000, null, this));

        return iFieldMap;
    }
}