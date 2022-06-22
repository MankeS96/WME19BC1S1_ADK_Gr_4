import React from "react";
import { useState, useEffect } from "react";
import './App.css';
import Plot from 'react-plotly.js';

function App() {

  // new line start
 const [signalWejsc, setPlotData] = useState({});
 const X = Object.keys(signalWejsc);
 useEffect(() => {
  fetch("/plot").then((res) =>
      res.json().then((signalWejsc) => {
          setPlotData(signalWejsc);
      })
  );
}, []);

 return (
  <div>
    <Plot
      data={[
        {
          x:  X,
          y: signalWejsc,
          type: 'line',
          marker: {color: 'red'}
        },
      ]}
      layout={ {width: "1500", height: 400, title: 'Sygnał wejściowy'} }
    />
  </div>
  
 );
}
export default App;
