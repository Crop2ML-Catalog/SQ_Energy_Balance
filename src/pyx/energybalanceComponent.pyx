from datetime import datetime
from math import *
from SQ_Energy_Balance.Netradiation import model_netradiation
from SQ_Energy_Balance.Netradiationequivalentevaporation import model_netradiationequivalentevaporation
from SQ_Energy_Balance.Priestlytaylor import model_priestlytaylor
from SQ_Energy_Balance.Conductance import model_conductance
from SQ_Energy_Balance.Diffusionlimitedevaporation import model_diffusionlimitedevaporation
from SQ_Energy_Balance.Penman import model_penman
from SQ_Energy_Balance.Ptsoil import model_ptsoil
from SQ_Energy_Balance.Soilevaporation import model_soilevaporation
from SQ_Energy_Balance.Evapotranspiration import model_evapotranspiration
from SQ_Energy_Balance.Soilheatflux import model_soilheatflux
from SQ_Energy_Balance.Potentialtranspiration import model_potentialtranspiration
from SQ_Energy_Balance.Cropheatflux import model_cropheatflux
from SQ_Energy_Balance.Canopytemperature import model_canopytemperature
def model_energybalance(float minTair,
      float maxTair,
      float albedoCoefficient,
      float stefanBoltzman,
      float elevation,
      float solarRadiation,
      float vaporPressure,
      float extraSolarRadiation,
      float lambdaV,
      float hslope,
      float psychrometricConstant,
      float Alpha,
      float vonKarman,
      float heightWeatherMeasurements,
      float zm,
      float d,
      float zh,
      float plantHeight,
      float wind,
      float deficitOnTopLayers,
      float soilDiffusionConstant,
      float VPDair,
      float rhoDensityAir,
      float specificHeatCapacityAir,
      float tau,
      float tauAlpha,
      int isWindVpDefined):
    cdef float netRadiation
    cdef float netOutGoingLongWaveRadiation
    cdef float netRadiationEquivalentEvaporation
    cdef float evapoTranspirationPriestlyTaylor
    cdef float conductance
    cdef float diffusionLimitedEvaporation
    cdef float evapoTranspirationPenman
    cdef float energyLimitedEvaporation
    cdef float soilEvaporation
    cdef float evapoTranspiration
    cdef float soilHeatFlux
    cdef float potentialTranspiration
    cdef float cropHeatFlux
    cdef float minCanopyTemperature
    cdef float maxCanopyTemperature
    netRadiation, netOutGoingLongWaveRadiation = model_netradiation( minTair,maxTair,albedoCoefficient,stefanBoltzman,elevation,solarRadiation,vaporPressure,extraSolarRadiation)
    conductance = model_conductance( vonKarman,heightWeatherMeasurements,zm,zh,d,plantHeight,wind)
    diffusionLimitedEvaporation = model_diffusionlimitedevaporation( deficitOnTopLayers,soilDiffusionConstant)
    netRadiationEquivalentEvaporation = model_netradiationequivalentevaporation( lambdaV,netRadiation)
    evapoTranspirationPriestlyTaylor = model_priestlytaylor( netRadiationEquivalentEvaporation,hslope,psychrometricConstant,Alpha)
    energyLimitedEvaporation = model_ptsoil( evapoTranspirationPriestlyTaylor,Alpha,tau,tauAlpha)
    evapoTranspirationPenman = model_penman( evapoTranspirationPriestlyTaylor,hslope,VPDair,psychrometricConstant,Alpha,lambdaV,rhoDensityAir,specificHeatCapacityAir,conductance)
    soilEvaporation = model_soilevaporation( diffusionLimitedEvaporation,energyLimitedEvaporation)
    evapoTranspiration = model_evapotranspiration( isWindVpDefined,evapoTranspirationPriestlyTaylor,evapoTranspirationPenman)
    soilHeatFlux = model_soilheatflux( netRadiationEquivalentEvaporation,tau,soilEvaporation)
    potentialTranspiration = model_potentialtranspiration( evapoTranspiration,tau)
    cropHeatFlux = model_cropheatflux( netRadiationEquivalentEvaporation,soilHeatFlux,potentialTranspiration)
    minCanopyTemperature, maxCanopyTemperature = model_canopytemperature( minTair,maxTair,cropHeatFlux,conductance,lambdaV,rhoDensityAir,specificHeatCapacityAir)
    return netRadiation, netOutGoingLongWaveRadiation, netRadiationEquivalentEvaporation, evapoTranspirationPriestlyTaylor, diffusionLimitedEvaporation, energyLimitedEvaporation, conductance, evapoTranspirationPenman, soilEvaporation, evapoTranspiration, potentialTranspiration, soilHeatFlux, cropHeatFlux, minCanopyTemperature, maxCanopyTemperature