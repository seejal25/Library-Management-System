import { useEffect, useState } from "react";
import GetAllBooksComponent from "./Components/GetAllBooksComponent";
import CreateBookComonent from "./Components/CreateBookComponent";
import UpdateBookComponent from "./Components/UpdateBookComponent";
import DeleteBookComponent from "./Components/DeleteBookComponent";

function App() {
  //https://chatgpt.com/s/t_6ae639bb330081918da5fa2c7b21e88b
  //https://chatgpt.com/s/t_6ae63a39cd60819185bdb2db1602adea
  /*const[count,setCount]=useState(0);
  const[name,setName]=useState("React");

  useEffect(() => {
    console.log("Library page loaded");
  }, []);

  useEffect(()=>{console.log("count ++")},[count]);
useEffect(()=>{console.log("name change")},[name]);

  return (
    <div>
      <h1>Library Management System</h1>
      <h2> Count: {count}</h2>
      <button 
      onClick={()=>setCount(count+1)}
      />

      <h2>Name: {name}</h2>
      <button
      onClick={()=>setName("Spring Boot")}
      />
    
    
    </div> 
    
  );*/

  return(
    <div>
      {/* <GetAllBooksComponent></GetAllBooksComponent> */}
      {/* <CreateBookComonent></CreateBookComonent> */}
      <UpdateBookComponent></UpdateBookComponent>
      <DeleteBookComponent></DeleteBookComponent>
      <GetAllBooksComponent></GetAllBooksComponent>
    </div>
  )
}

export default App;