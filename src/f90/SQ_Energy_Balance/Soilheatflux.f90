SUBROUTINE model_soilheatflux(netRadiationEquivalentEvaporation, &
        tau, &
        soilEvaporation, &
        soilHeatFlux)
    IMPLICIT NONE
    REAL, INTENT(IN) :: netRadiationEquivalentEvaporation
    REAL, INTENT(IN) :: tau
    REAL, INTENT(IN) :: soilEvaporation
    REAL, INTENT(OUT) :: soilHeatFlux
    !- Name: SoilHeatFlux -Version: 1.0, -Time step: 1
    !- Description:
    !            * Title: SoilHeatFlux Model
    !            * Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    !            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    !            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    !            New Zealand Institute for Crop and Food Research Ltd.,
    !            New Zealand Institute for Crop and Food Research Ltd.,
    !            New Zealand Institute for Crop and Food Research Ltd.
    !        
    !            * ExtendedDescription: The available energy in the soil 
    !            * ShortDescription: The available energy in the soil
    !- inputs:
    !            * name: netRadiationEquivalentEvaporation
    !                          ** variablecategory : state
    !                          ** description : net Radiation Equivalent Evaporation
    !                          ** datatype : DOUBLE
    !                          ** default : 638.142
    !                          ** min : 0
    !                          ** max : 5000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    !                          ** inputtype : variable
    !            * name: tau
    !                          ** description : plant cover factor
    !                          ** parametercategory : species
    !                          ** datatype : DOUBLE
    !                          ** default : 0.9983
    !                          ** min : 0
    !                          ** max : 100
    !                          ** unit : 
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    !                          ** inputtype : parameter
    !            * name: soilEvaporation
    !                          ** description : soil Evaporation
    !                          ** variablecategory : state
    !                          ** datatype : DOUBLE
    !                          ** default : 448.240
    !                          ** min : 0
    !                          ** max : 10000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    !                          ** inputtype : variable
    !- outputs:
    !            * name: soilHeatFlux
    !                          ** description : soil Heat Flux 
    !                          ** variablecategory : rate
    !                          ** datatype : DOUBLE
    !                          ** min : 0
    !                          ** max : 10000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    soilHeatFlux = tau * netRadiationEquivalentEvaporation -  &
            soilEvaporation
END SUBROUTINE model_soilheatflux
