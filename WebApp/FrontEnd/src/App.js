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

const [signalObw, setPlotObw] = useState({
  env: 0,
  rec: 0
});

useEffect(() => {
  fetch("/obwiednia").then((res) =>
      res.json().then((signalObw) => {
        setPlotObw({
          env: signalObw.env,
          rec: signalObw.rec
        });
      })
  );
}, []);
const XX = Object.keys(signalObw.rec);

const [signalWidmo, setPlotWidmo] = useState({
  freq: 0,
  power: 0
});

useEffect(() => {
  fetch("/widmo").then((res) =>
      res.json().then((signalWidmo) => {
        setPlotWidmo({
          freq: signalWidmo.freq,
          power: signalWidmo.power
        });
      })
  );
}, []);

const [spectogram, setPlotSpectogram] = useState({
  specFreq: 0,
  specTime: 0
});

useEffect(() => {
  fetch("/spektogram").then((res) =>
      res.json().then((spectogram) => {
        setPlotSpectogram({
          specFreq: spectogram.specFreq,
          specTime: spectogram.specTime
        });
      })
  );
}, []);

 return (
  <div>
    <div>
      <Plot
        data={[
          {
            x: X,
            y: signalWejsc,
            type: 'line',
            marker: {color: 'blue'}
          },
        ]}
        layout={ {width: "1500", height: 400, title: 'Sygnał wejściowy'} }
      />
    </div>
    <div>
      <Plot
        data={[
          {
            x: XX,
            y: signalObw.rec,
            type: 'line',
            marker: {color: 'blue'}
          },
          {type: 'line', x: XX, y: signalObw.env, marker: {color: 'red'}},
        ]}
        layout={ {width: "1500", height: 400, title: 'Obwiednia'} }
      />
    </div>
    <div>
      <Plot
        data={[
          {
            x: signalWidmo.freq,
            y: signalWidmo.power,
            type: 'line',
            marker: {color: 'blue'}
          },
        ]}
        layout={ {width: "1500", height: 400, title: 'Widmo'} }
      />
    </div>
    <div>
      <Plot
          data={[
            {
              x: spectogram.specTime,
              y: spectogram.specFreq,
              type: 'line',
            },
          ]}
          layout={ {width: "1500", height: 400, title: 'Spektogram'} }
        />
    </div>
  </div>

  
 );
}
export default App;
