import  java.io.*;
import  java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
public class Penman
{
    private Double psychrometricConstant;
    public Double getpsychrometricConstant()
    { return psychrometricConstant; }

    public void setpsychrometricConstant(Double _psychrometricConstant)
    { this.psychrometricConstant= _psychrometricConstant; } 
    
    private Double Alpha;
    public Double getAlpha()
    { return Alpha; }

    public void setAlpha(Double _Alpha)
    { this.Alpha= _Alpha; } 
    
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
    
    public Penman() { }
    public void  Calculate_Model(EnergyBalanceState s, EnergyBalanceState s1, EnergyBalanceRate r, EnergyBalanceAuxiliary a,  EnergyBalanceExogenous ex)
    {
        //- Name: Penman -Version: 1.0, -Time step: 1
        //- Description:
    //            * Title: Penman Model
    //            * Authors: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: It uses Penmann-Monteith method vase on the availability of wind and vapor pressure daily data
    //            * ShortDescription: It uses Penmann-Monteith method vase on the availability of wind and vapor pressure daily data
        //- inputs:
    //            * name: evapoTranspirationPriestlyTaylor
    //                          ** description : evapoTranspiration of Priestly Taylor 
    //                          ** variablecategory : rate
    //                          ** datatype : DOUBLE
    //                          ** default : 449.367
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: hslope
    //                          ** description : the slope of saturated vapor pressure temperature curve at a given temperature 
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** default : 0.584
    //                          ** min : 0
    //                          ** max : 1000
    //                          ** unit : hPa °C-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: VPDair
    //                          ** description :  vapour pressure density
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** default : 2.19
    //                          ** min : 0
    //                          ** max : 1000
    //                          ** unit : hPa
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: psychrometricConstant
    //                          ** description : psychrometric constant
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 0.66
    //                          ** min : 0
    //                          ** max : 1
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: Alpha
    //                          ** description : Priestley-Taylor evapotranspiration proportionality constant
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 1.5
    //                          ** min : 0
    //                          ** max : 100
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: lambdaV
    //                          ** description : latent heat of vaporization of water
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 2.454
    //                          ** min : 0
    //                          ** max : 10
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: rhoDensityAir
    //                          ** description : Density of air
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 1.225
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: specificHeatCapacityAir
    //                          ** description : Specific heat capacity of dry air
    //                          ** parametercategory : constant
    //                          ** datatype : DOUBLE
    //                          ** default : 0.00101
    //                          ** min : 0
    //                          ** max : 1
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: conductance
    //                          ** description : conductance
    //                          ** variablecategory : state
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** default : 598.685
    //                          ** unit : m d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
        //- outputs:
    //            * name: evapoTranspirationPenman
    //                          ** description :  evapoTranspiration of Penman Monteith
    //                          ** variablecategory : rate
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 5000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
        Double evapoTranspirationPriestlyTaylor = r.getevapoTranspirationPriestlyTaylor();
        Double hslope = a.gethslope();
        Double VPDair = a.getVPDair();
        Double conductance = s.getconductance();
        Double evapoTranspirationPenman;
        evapoTranspirationPenman = evapoTranspirationPriestlyTaylor / Alpha + (1000.0d * (rhoDensityAir * specificHeatCapacityAir * VPDair * conductance / (lambdaV * (hslope + psychrometricConstant))));
        r.setevapoTranspirationPenman(evapoTranspirationPenman);
    }
}