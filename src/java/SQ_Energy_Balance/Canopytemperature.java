import  java.io.*;
import  java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
public class CanopyTemperature
{
    private Double lambdaV;
    public Double getlambdaV()
    { return lambdaV; }

    public void setlambdaV(Double _lambdaV)
    { this.lambdaV= _lambdaV; } 
    
    private Double rhoDensityAir;
    public Double getrhoDensityAir()
    { return rhoDensityAir; }

    public void setrhoDensityAir(Double _rhoDensityAir)
    { this.rhoDensityAir= _rhoDensityAir; } 
    
    private Double specificHeatCapacityAir;
    public Double getspecificHeatCapacityAir()
    { return specificHeatCapacityAir; }

    public void setspecificHeatCapacityAir(Double _specificHeatCapacityAir)
    { this.specificHeatCapacityAir= _specificHeatCapacityAir; } 
    
    public CanopyTemperature() { }
    public void  Calculate_Model(EnergyBalanceState s, EnergyBalanceState s1, EnergyBalanceRate r, EnergyBalanceAuxiliary a,  EnergyBalanceExogenous ex)
    {
        //- Name: CanopyTemperature -Version: 1.0, -Time step: 1
        //- Description:
    //            * Title: CanopyTemperature Model
    //            * Authors: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference: https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: It is calculated from the crop heat flux and the boundary layer conductance 
    //            * ShortDescription: It is calculated from the crop heat flux and the boundary layer conductance 
        //- inputs:
    //            * name: minTair
    //                          ** description : minimum air temperature
    //                          ** datatype : DOUBLE
    //                          ** variablecategory : auxiliary
    //                          ** min : -30
    //                          ** max : 45
    //                          ** default : 0.7
    //                          ** unit : degC
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: maxTair
    //                          ** description : maximum air Temperature
    //                          ** datatype : DOUBLE
    //                          ** variablecategory : auxiliary
    //                          ** min : -30
    //                          ** max : 45
    //                          ** default : 7.2
    //                          ** unit : degC
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: cropHeatFlux
    //                          ** description : Crop heat flux
    //                          ** variablecategory : rate
    //                          ** inputtype : variable
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** default : 447.912
    //                          ** unit : g/m**2/d
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //            * name: conductance
    //                          ** description : the boundary layer conductance
    //                          ** variablecategory : state
    //                          ** inputtype : variable
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** default : 598.685
    //                          ** unit : m/d
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //            * name: lambdaV
    //                          ** description : latent heat of vaporization of water
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 2.454
    //                          ** min : 0
    //                          ** max : 10
    //                          ** unit : MJ/kg
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: rhoDensityAir
    //                          ** description : Density of air
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 1.225
    //                          ** unit : kg/m**3
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: specificHeatCapacityAir
    //                          ** description : Specific heat capacity of dry air
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 0.00101
    //                          ** unit : MJ/kg/degC
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
        //- outputs:
    //            * name: minCanopyTemperature
    //                          ** description : minimal Canopy Temperature  
    //                          ** datatype : DOUBLE
    //                          ** variablecategory : state
    //                          ** min : -30
    //                          ** max : 45
    //                          ** unit : degC
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //            * name: maxCanopyTemperature
    //                          ** description : maximal Canopy Temperature 
    //                          ** datatype : DOUBLE
    //                          ** variablecategory : state
    //                          ** min : -30
    //                          ** max : 45
    //                          ** unit : degC
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
        Double minTair = a.getminTair();
        Double maxTair = a.getmaxTair();
        Double cropHeatFlux = r.getcropHeatFlux();
        Double conductance = s.getconductance();
        Double minCanopyTemperature;
        Double maxCanopyTemperature;
        minCanopyTemperature = minTair + (cropHeatFlux / (rhoDensityAir * specificHeatCapacityAir * conductance / lambdaV * 1000.0d));
        maxCanopyTemperature = maxTair + (cropHeatFlux / (rhoDensityAir * specificHeatCapacityAir * conductance / lambdaV * 1000.0d));
        s.setminCanopyTemperature(minCanopyTemperature);
        s.setmaxCanopyTemperature(maxCanopyTemperature);
    }
}