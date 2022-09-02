import numpy 
from math import *
def model_soilheatflux(float netRadiationEquivalentEvaporation,
                       float tau,
                       float soilEvaporation):
    """

    SoilHeatFlux Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
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


