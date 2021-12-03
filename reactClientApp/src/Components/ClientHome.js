import React, { useState, useEffect } from 'react';
import { Jumbotron, Container, Row, Col } from "react-bootstrap"
import Form from "../Components/Form/Form"

import '../App.css';

const ClientHome = () => {


    return (
        <>
            <Jumbotron fluid>
                <Container>
                    <h1>Client Home.</h1>
                    <p>
                        This is a modified jumbotron that occupies the entire horizontal space of
                        its parent.
                    </p>
                </Container>
            </Jumbotron>

            <Container fluid>
                <Form/>
            </Container>

        </>
    )
}

export default ClientHome;