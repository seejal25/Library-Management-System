import React, { useEffect, useState } from "react";
import axios from "axios";

function GetAllBooksComponent(){
    const [books,setBooks]=useState(null);
    const [error,setError]=useState("");
    useEffect(()=>{
        axios.get("http://localhost:8080/api/books/getDetails")
        .then(response=>{
            setBooks(response.data);
        })
        .catch(error=>
            {
                console.log(error)
                setError(error.message)
            })
    },[])
    return(
        <div>
            <center>
                <h2>Get Book Details</h2>
                <h4> 
                    {
                        books?
                        <table>
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Book Title</th>
                                <th>Author</th>
                                <th>Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            
                               {books.map(book=>
                                 <tr key={book.id}>
                                    <td>{book.id}</td>
                                    <td>{book.title}</td>
                                    <td>{book.author}</td>
                                    <td>{book.price}</td>

                                 </tr>
                               )
                               }
                               </tbody>
                            
                        </table>
                        :error
                    }

                </h4>
            </center>

            {/* <h1>get all books details</h1>
            {books.map(
                book=>(
                    <p key={book.id}>
                        {book.title}-{book.author}-{book.price}
                    </p>
                )
            )
            } */}
        </div>

    );
}

export default GetAllBooksComponent;