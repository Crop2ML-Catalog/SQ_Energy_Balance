import numpy 
from math import *
def model_soilheatflux(float netRadiationEquivalentEvaporation=638.142,
                       float tau=0.9983,
                       float soilEvaporation=448.24):
    """

    SoilHeatFlux Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference: Modelling energy balance in the wheat crop model SiriusQuality2:
            Evapotranspiration and canopy and soil temperature calculations
    Institution: New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.,
            New Zealand Institute for Crop and Food Research Ltd.
        
    ExtendedDescription: The available energy in the soil 
    ShortDescription: The available energy in the soil

    """
    cdef float soilHeatFlux
    soilHeatFlux = tau * netRadiationEquivalentEvaporation - soilEvaporation
    return  soilHeatFlux
