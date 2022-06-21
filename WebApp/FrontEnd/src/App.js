import React from "react";
// import { useState } from "react";
import './App.css';
// import axios from "axios";
import ReactSignalsPlot from "react-signals-plot";

// function App() {

//   // new line start
//  const [plotData, setPlotData] = useState(null)

//  function getData() {
//    axios({
//      method: "GET",
//      url:"/plot",
//    })
//    .then((response) => {
//      const res =response.data
//      setPlotData(({
//        signalData: res.data}))
//    }).catch((error) => {
//      if (error.response) {
//        console.log(error.response)
//        console.log(error.response.status)
//        console.log(error.response.headers)
//        }
//    })}
//    //end of new line 
//  return (
//   <ReactSignalsPlot
//     style={{ width: "100%", height: 500 }}
//     data={plotData.signalData}
//     samplesLimit={300}
//     interactive={true}
//    />
//  );
// }
// export default App;

const series = {
  data: [
    {
      id: "EX",
      values: [
        { x: 1, y: 5 },
        { x: 2, y: 10 },
        { x: 3, y: 1 },
        { x: 4, y: 3 },
        { x: 5, y: 12 },
        { x: 6, y: 4 },
        { x: 7, y: 0 },
        { x: 8, y: 13 },
        { x: 9, y: 7 }
      ]
    }
  ],
  labels: {
    x: "X, seconds",
    y: "Y, volts"
  }
};

class PlotExample extends React.Component {
  render() {
    return (
      <ReactSignalsPlot
        style={{ width: "100%", height: 400 }}
        data={series.data}
        labels={series.labels}
        interactive={true}
      />
    );
  }
}

export default PlotExample;
