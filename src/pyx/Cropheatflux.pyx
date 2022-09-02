import numpy 
from math import *
def model_cropheatflux(float netRadiationEquivalentEvaporation,
                       float soilHeatFlux,
                       float potentialTranspiration):
    """

    CropHeatFlux Model
    Author: Peter D. Jamieson, Glen S. Francis, Derick R. Wilson, Robert J. Martin
    Reference:  https://doi.org/10.1016/0168-1923(94)02214-5
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


