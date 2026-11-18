import axios from "axios";
import { useState } from "react";

function UpdateBookComponent(){
    const [id,setId]=useState("");
    const [title,setTitle]=useState("");
    const[author,setAuthor]=useState("");
    const [price,setPrice]=useState(0);

    const [newOutputdata,setNewOutputData]=useState(null);
    const [newErrorMessage, setErrorMessage] = useState("");

    function submitHandler(event){
        
        const book={
            id:Number(id),
            title:title,
            author:author,
            price: Number(price)
        }
        //axios shd be inside submitHandler , or else 'book' cant be used
        axios.put("http://localhost:8080/api/books/updateBook",book)
        .then(response=>
        {console.log("Put or update done",response);
            setNewOutputData("Updated successfully");
           // setNewOutputData(response.data);
            //https://chatgpt.com/s/t_6ae74c192734819197334da2f786445d
            setErrorMessage("");

            setAuthor("");
            setTitle("");
            setPrice("");

        }
    )
    .catch(
        error=>{
            console.log(error);
            setNewOutputData(null);
            setErrorMessage(error.message);
        }
    )
    };

    
    return (
        <div>
            <center>
                <h1>Enter Book Details to update</h1>
               {/* <form onSubmit={submitHandler}> */}
                
                <table>
                    <tr>
                        <th>Book Id</th>
                        <td> 
                            <input
                            type="text" value={id} onChange={(event)=>setId(event.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Book Title</th>
                        <td> 
                            <input
                            type="text" value={title} onChange={(event)=>setTitle(event.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Book Author</th>
                        <td>
                            <input 
                            type="text" value={author} onChange={(event)=>setAuthor(event.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>Book price</th>
                        <td>
                            <input 
                            type="text" value={price} onChange={(event)=>setPrice(event.target.value)}
                            />
                        </td>
                    </tr>

                </table>
                <button type="button" onClick={submitHandler}>
                    Update Book
                </button>
               {/* </form> */}
               <h4>{newOutputdata}</h4>
               <h4>{newErrorMessage}</h4>
            </center>
        </div>
    )
}
export default UpdateBookComponent;