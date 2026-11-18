import axios from "axios";
import { useState } from "react";

function CreateBookComonent(){
    const [title,setTitle]=useState("");
    const[author,setAuthor]=useState("");
    const [price,setPrice]=useState(0);

    const [newOutputdata,setNewOutputData]=useState(null);
    const [newErrorMessage, setErrorMessage] = useState("");

    function submitHandler(event){
        
        const book={
            title:title,
            author:author,
            price: Number(price)
        }
        //axios shd be inside submitHandler , or else 'book' cant be used
        axios.post("http://localhost:8080/api/books/addBook",book)
        .then(response=>
        {console.log("POst",response);
            setNewOutputData(response.data);
            setErrorMessage("");

            setAuthor("");
            setTitle("");
            setPrice("");

        }
    )
    .catch(
        error=>{
            console.log(error);
            setNewOutputData("");
            setErrorMessage(error.message);
        }
    )
    };

    
    return (
        <div>
            <center>
                <h1>Enter Book Details</h1>
               {/* <form onSubmit={submitHandler}> */}
                
                <table>
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
                    Create Book
                </button>
               {/* </form> */}
               <h4>{newOutputdata}</h4>
               <h4>{newErrorMessage}</h4>
            </center>
        </div>
    )
}
export default CreateBookComonent;