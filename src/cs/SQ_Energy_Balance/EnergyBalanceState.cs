using System;
using System.Collections.Generic;
public class EnergyBalanceState 
{
    private double _diffusionLimitedEvaporation;
    private double _conductance;
    private double _minCanopyTemperature;
    private double _maxCanopyTemperature;
    
    public EnergyBalanceState() { }
    
    
    public EnergyBalanceState(EnergyBalanceState toCopy, bool copyAll) // copy constructor 
    {
    if (copyAll)
    {
    
    _diffusionLimitedEvaporation = toCopy._diffusionLimitedEvaporation;
    _conductance = toCopy._conductance;
    _minCanopyTemperature = toCopy._minCanopyTemperature;
    _maxCanopyTemperature = toCopy._maxCanopyTemperature;
    }
    }
    public double diffusionLimitedEvaporation
        {
            get { return this._diffusionLimitedEvaporation; }
            set { this._diffusionLimitedEvaporation= value; } 
        }
    public double conductance
        {
            get { return this._conductance; }
            set { this._conductance= value; } 
        }
    public double minCanopyTemperature
        {
            get { return this._minCanopyTemperature; }
            set { this._minCanopyTemperature= value; } 
        }
    public double maxCanopyTemperature
        {
            get { return this._maxCanopyTemperature; }
            set { this._maxCanopyTemperature= value; } 
        }
}