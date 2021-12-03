import React, { useRef, useState, useEffect} from "react"
import { useParams, Link } from 'react-router-dom'
import { AuthContext, SignInMethod } from "../Contexts/AuthContext"



const Product = () => {

const { userToken, removeToken, signInMethod} = AuthContext()

const [apidata, setData] = useState([])
const [loading, setLoading] = useState(true)

    let content = null

    // const { id } = useParams()
    // console.log('params: ' + id)
    //let url = `http://localhost:3001/comments?id=${id}`

    let url = `https://pokeapi.co/api/v2/pokemon?limit=30`

    useEffect(() => {
        obtenerDatos();
    },[])

    const obtenerDatos = async () => {

        const response = await fetch(url, {
            // headers: {Authentication: `Bearer ${userToken}`}
          });

        const jsonresponse = await response.json();
        setData(jsonresponse.results);
        setLoading(false);

    }

    if (loading)
        content = <p>loading ...</p>

    if (!loading){
        content =
            <div>
                <h1>Pokemons</h1>

                    {
                        apidata.map(
                            item => (
                                <li key={item.name}>
                                    <Link to={{ pathname: `${item.url}` }} target="_blank"> {item.id} {item.url}</Link>
                                </li>
                            )
                        )
                    }

            </div>
    }
    return(
        <div>{content}</div>
    )
}
export default Product