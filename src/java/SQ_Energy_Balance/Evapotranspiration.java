import  java.io.*;
import  java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
public class Evapotranspiration
{
    private int isWindVpDefined;
    public int getisWindVpDefined()
    { return isWindVpDefined; }

    public void setisWindVpDefined(int _isWindVpDefined)
    { this.isWindVpDefined= _isWindVpDefined; } 
    
    public Evapotranspiration() { }
    public void  Calculate_evapotranspiration(EnergybalanceState s, EnergybalanceState s1, EnergybalanceRate r, EnergybalanceAuxiliary a,  EnergybalanceExogenous ex)
    {
        //- Name: EvapoTranspiration -Version: 1.0, -Time step: 1
        //- Description:
    //            * Title: Evapotranspiration Model
    //            * Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    //            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    //            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.,
    //            New Zealand Institute for Crop and Food Research Ltd.
    //        
    //            * ExtendedDescription: According to the availability of wind and/or vapor pressure daily data, the
    //            SiriusQuality2 model calculates the evapotranspiration rate using the Penman (if wind
    //            and vapor pressure data are available) (Penman 1948) or the Priestly-Taylor
    //            (Priestley and Taylor 1972) method 
    //            * ShortDescription: It uses to choose evapotranspiration of Penmann or Priestly-Taylor 
        //- inputs:
    //            * name: isWindVpDefined
    //                          ** description : if wind and vapour pressure are defined
    //                          ** parametercategory : constant
    //                          ** datatype : INT
    //                          ** default : 1
    //                          ** min : 0
    //                          ** max : 1
    //                          ** unit : 
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : parameter
    //            * name: evapoTranspirationPriestlyTaylor
    //                          ** description : evapoTranspiration of Priestly Taylor 
    //                          ** variablecategory : rate
    //                          ** default : 449.367
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : mm
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
    //            * name: evapoTranspirationPenman
    //                          ** description : evapoTranspiration of Penman 
    //                          ** datatype : DOUBLE
    //                          ** variablecategory : rate
    //                          ** default : 830.958
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : mm
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    //                          ** inputtype : variable
        //- outputs:
    //            * name: evapoTranspiration
    //                          ** description : evapoTranspiration
    //                          ** variablecategory : rate
    //                          ** datatype : DOUBLE
    //                          ** min : 0
    //                          ** max : 10000
    //                          ** unit : mm
    //                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
        double evapoTranspirationPriestlyTaylor = r.getevapoTranspirationPriestlyTaylor();
        double evapoTranspirationPenman = r.getevapoTranspirationPenman();
        double evapoTranspiration;
        if (isWindVpDefined == 1)
        {
            evapoTranspiration = evapoTranspirationPenman;
        }
        else
        {
            evapoTranspiration = evapoTranspirationPriestlyTaylor;
        }
        r.setevapoTranspiration(evapoTranspiration);
    }
}