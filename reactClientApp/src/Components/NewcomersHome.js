import React from 'react'
import { Jumbotron, Container, Card } from "react-bootstrap"
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import logo from '../logo.svg';
import '../App.css';

function NewcomersHome() {

    return (
        <>
            <Jumbotron fluid>
                <Container>
                    <h1>This page is public.</h1>
                    <p>
                        This is a modified jumbotron that occupies the entire horizontal space of
                        its parent.
                    </p>
                </Container>
            </Jumbotron>

            <Card>
                <Card.Header as="h5" className="text-center">This page is public.</Card.Header>

                <Container>
                    <Row className="justify-content-md-center" style={{ paddingTop: '20px' }} >
                        {/* <Col md="auto">{currentUser && JSON.stringify(currentUser.email)}</Col> */}
                        {/* <Col md="auto">{userToken && JSON.stringify(userToken)}</Col> */}
                    </Row>
                </Container>

                <Card.Body>
                    <div className="App-header">
                        <img src={logo} className="App-logo" alt="logo" />
                        <p>Edit <code>src/App.js</code> and save to reload.</p>
                        <a className="App-link" href="https://reactjs.org" target="_blank" rel="noopener noreferrer"> Learn React </a>
                    </div>
                </Card.Body>
            </Card>

        </>
    )
}
export default NewcomersHome