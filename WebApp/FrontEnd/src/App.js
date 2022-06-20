import React, {useState} from "react";
import './App.css';
import axios from "axios";

function App() {

    // new line start
   const [profileData, setProfileData] = useState(null)
 
   function getData() {
     axios({
       method: "GET",
       url:"/test",
     })
     .then((response) => {
       const res =response.data
       setProfileData(({
         imie: res.imie,
         nazwisko: res.nazwisko}))
     }).catch((error) => {
       if (error.response) {
         console.log(error.response)
         console.log(error.response.status)
         console.log(error.response.headers)
         }
     })}
     //end of new line 
 
   return (
     <div className="App">
       <header className="App-header">
         {/* new line start*/}
         <p>To get your profile details: </p><button onClick={getData}>Click me</button>
         {profileData && <div>
               <p>Imie: {profileData.imie}</p>
               <p>Nazwisko: {profileData.nazwisko}</p>
             </div>
         }
          {/* end of new line */}
       </header>
     </div>
   );
 }

export default App;
