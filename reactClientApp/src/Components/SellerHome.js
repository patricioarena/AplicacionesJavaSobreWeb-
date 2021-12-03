import React, { useState, useEffect } from 'react';
import { Jumbotron, Container, Button } from "react-bootstrap"


import Map from '../Components/Map/Map';


function SellerHome() {

    const centerMap = [51.505, -0.09]
    const [state, setState] = useState()

    const handleTest = () => {
        console.log(state);
    }

    return (
        <>
            {/* <Jumbotron fluid>
                <Container>
                    <h1>Seller Home.</h1>
                    <p>
                        This is a modified jumbotron that occupies the entire horizontal space of
                        its parent.
                    </p>
                </Container>
            </Jumbotron> */}

            <Button onClick={ handleTest } > Click Here</Button>

            <div>{state}</div>

            <Container style={{ height: '50em',
                            width: '50em'}}>
                <Map
                    centerMap={centerMap}
                    activeMarker={true}
                    activeArea={true}
                    activeSearch={true}
                    activeSavePosition={true}
                    style={{ 'width': '8em' }}
                    setState = {setState} // Con esta propiedad se puede setear desde el boton Save Map el centro a un estado externo al componente Map
                />

            </Container>

        </>
    )
}
export default SellerHome
