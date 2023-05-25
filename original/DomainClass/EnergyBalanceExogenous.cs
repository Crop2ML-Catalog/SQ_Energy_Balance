
using System;
using System.Collections.Generic;
using System.Reflection;

namespace SiriusQualityEnergyBalance.DomainClass
{
    public class EnergyBalanceExogenous
    {
        private double _tau;
        public EnergyBalanceExogenous(EnergyBalanceExogenous toCopy, bool copyAll) // copy constructor 
        {
            if (copyAll)
            {
                _tau = toCopy._tau;
            }
        }

        public double tau
        {
            get { return this._tau; }
            set { this._tau= value; } 
        }

        public virtual Boolean ClearValues()
        {
             _tau = default(double);
            return true;
        }

    }
}