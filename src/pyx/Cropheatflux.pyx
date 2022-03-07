import numpy 
from math import *
def model_cropheatflux(float netRadiationEquivalentEvaporation=638.142,
                       float soilHeatFlux=188.817,
                       float potentialTranspiration=1.413):
    """

    CropHeatFlux Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: abModelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: It is calculated from net Radiation, soil heat flux and potential transpiration 
    ShortDescription: It calculates the crop heat flux
        

    """
    cdef float cropHeatFlux
    cropHeatFlux = netRadiationEquivalentEvaporation - soilHeatFlux - potentialTranspiration
    return  cropHeatFlux
