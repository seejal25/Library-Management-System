import axios from "axios";
import { useState } from "react";

function DeleteBookComponent(){
    const [id,setId]=useState('');
    const[message,setMsg]=useState("");
    //setmsg -> updates states 
    //message-> saves current value

    function deleteHandler(){
        axios.delete("http://localhost:8080/api/books/deleteBook/"+id)
        .then(response=>setMsg("deleted successfully "))
        .catch(error=>setMsg(error.response?.data || error.message))
    }

    return (
        <div>
            <table>
                <tr>
                    <th>Enter Id</th>
                    <td>
                        <input type="text" value={id} onChange={(event)=>setId(event.target.value)}/>
                    </td>
                </tr>
            </table>
            <button type="button" onClick={deleteHandler}>
                Delete
            </button>
            <h4>{message}</h4>
            
        </div>
    )
}
export default DeleteBookComponent;