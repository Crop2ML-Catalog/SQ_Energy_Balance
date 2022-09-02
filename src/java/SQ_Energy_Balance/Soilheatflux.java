import  java.io.*;
import  java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
public class Soilheatflux
{
    private double tau;
    public double gettau()
    { return tau; }

    public void settau(double _tau)
    { this.tau= _tau; } 
    
    public Soilheatflux() { }
    public void  Calculate_soilheatflux(EnergybalanceState s, EnergybalanceState s1, EnergybalanceRate r, EnergybalanceAuxiliary a,  EnergybalanceExogenous ex)
    {
        //- Name: SoilHeatFlux -Version: 1.0, -Time step: 1
        //- Description:
    //            * Title: SoilHeatFlux Model
    //            * Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: The available energy in the soil 
    //            * ShortDescription: The available energy in the soil
        //- inputs:
    //            * name: netRadiationEquivalentEvaporation
    //                          ** variablecategory : auxiliary
    //                          ** description : net Radiation Equivalent Evaporation
    //                          ** datatype : DOUBLE
    //                          ** default : 638.142
    //                          ** min : 0
    //                          ** max : 5000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: tau
    //                          ** description : plant cover factor
    //                          ** parametercategory : species
    //                          ** datatype : DOUBLE
    //                          ** default : 0.9983
    //                          ** min : 0
    //                          ** max : 100
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: soilEvaporation
    //                          ** description : soil Evaporation
    //                          ** variablecategory : auxiliary
    //                          ** datatype : DOUBLE
    //                          ** default : 448.240
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
        //- outputs:
    //            * name: soilHeatFlux
    //                          ** description : soil Heat Flux 
    //                          ** variablecategory : rate
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : g m-2 d-1
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
        double netRadiationEquivalentEvaporation = a.getnetRadiationEquivalentEvaporation();
        double soilEvaporation = a.getsoilEvaporation();
        double soilHeatFlux;
        soilHeatFlux = tau * netRadiationEquivalentEvaporation - soilEvaporation;
        r.setsoilHeatFlux(soilHeatFlux);
    }
}