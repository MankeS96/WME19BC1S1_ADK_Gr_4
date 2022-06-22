import React from "react";
import { useState, useEffect } from "react";
import './App.css';
import ReactSignalsPlot from "react-signals-plot";
import Plot from 'react-plotly.js';

function App() {

  // new line start
 const [signal, setPlotData] = useState({});
 const X = Object.keys(signal);
 useEffect(() => {
  // Using fetch to fetch the api from 
  // flask server it will be redirected to proxy
  fetch("/plot").then((res) =>
      res.json().then((signal) => {
          // Setting a data from api
          setPlotData(signal);
      })
  );
}, []);

 return (
  <Plot
  data={[
    {
      x:  X,
      y: signal,
      type: 'line',
      marker: {color: 'red'}
    },
  ]}
  layout={ {width: "1500", height: 400, title: 'Sygnał wejściowy'} }
/>
 );
}
export default App;
