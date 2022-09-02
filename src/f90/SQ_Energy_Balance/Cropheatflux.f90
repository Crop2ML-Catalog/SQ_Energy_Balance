SUBROUTINE model_cropheatflux(netRadiationEquivalentEvaporation, &
        soilHeatFlux, &
        potentialTranspiration, &
        cropHeatFlux)
    IMPLICIT NONE
    REAL, INTENT(IN) :: netRadiationEquivalentEvaporation
    REAL, INTENT(IN) :: soilHeatFlux
    REAL, INTENT(IN) :: potentialTranspiration
    REAL, INTENT(OUT) :: cropHeatFlux
    !- Name: CropHeatFlux -Version: 1.0, -Time step: 1
    !- Description:
    !            * Title: CropHeatFlux Model
    !            * Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    !            * Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
    !            * Institution: New Zealand Institute for Crop and Food Research Ltd.,
    !            New Zealand Institute for Crop and Food Research Ltd.,
    !            New Zealand Institute for Crop and Food Research Ltd.,
    !            New Zealand Institute for Crop and Food Research Ltd.
    !        
    !            * ExtendedDescription: It is calculated from net Radiation, soil heat flux and potential transpiration 
    !            * ShortDescription: It calculates the crop heat flux
    !        
    !- inputs:
    !            * name: netRadiationEquivalentEvaporation
    !                          ** variablecategory : auxiliary
    !                          ** description : net Radiation Equivalent Evaporation
    !                          ** datatype : DOUBLE
    !                          ** default : 638.142
    !                          ** min : 0
    !                          ** max : 10000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    !                          ** inputtype : variable
    !            * name: soilHeatFlux
    !                          ** description : soil Heat Flux
    !                          ** variablecategory : rate
    !                          ** datatype : DOUBLE
    !                          ** default : 188.817
    !                          ** min : 0
    !                          ** max : 1000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    !                          ** inputtype : variable
    !            * name: potentialTranspiration
    !                          ** description : potential Transpiration
    !                          ** variablecategory : rate
    !                          ** datatype : DOUBLE
    !                          ** default :  1.413
    !                          ** min : 0
    !                          ** max : 1000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    !                          ** inputtype : variable
    !- outputs:
    !            * name: cropHeatFlux
    !                          ** description :  crop Heat Flux
    !                          ** variablecategory : rate
    !                          ** datatype : DOUBLE
    !                          ** min : 0
    !                          ** max : 10000
    !                          ** unit : g m-2 d-1
    !                          ** uri : http://www1.clermont.inra.fr/siriusquality/?page_id=547
    cropHeatFlux = netRadiationEquivalentEvaporation - soilHeatFlux -  &
            potentialTranspiration
END SUBROUTINE model_cropheatflux
