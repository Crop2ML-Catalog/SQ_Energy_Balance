h = max(10, plantHeight) / 100.0
conductance = (wind * pow(vonKarman, 2)) / (log((heightWeatherMeasurements - d * h) / (zm * h)) * log((heightWeatherMeasurements - d * h) / (zh * h)))

