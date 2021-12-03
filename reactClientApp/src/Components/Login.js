import React, { useRef, useState, useEffect } from "react"
import { Container, Form, Button, Card, Alert } from "react-bootstrap"
import { Link, useHistory } from "react-router-dom"
import { AuthContext, SignInMethod } from "../Contexts/AuthContext"
import SignInWithGoogle from "./SignInWithGoogle"

export default function Login() {
    const emailRef = useRef()
    const passwordRef = useRef()

    const { userToken } = AuthContext()

    const [error, setError] = useState("")
    const [success, setSuccess] = useState("")
    const [loading, setLoading] = useState(false)
    const history = useHistory()

    async function handleSubmit(e) {
        e.preventDefault()

        try {
            setError("")
            setSuccess("")
            setLoading(true)
            // let response = await login(emailRef.current.value, passwordRef.current.value)
            //             console.log(response);
            //             if (response == true) {
            //                 setSuccess(`Success login!`)
            //    //             history.push("/newcomershome")
            //             } else {
            //                 setError("Failed to log in")
            //             }
        } catch {
            setError("Failed to log in")
        }
        setLoading(false)

    }

    return (
        <>
            <div md="auto">{userToken && JSON.stringify(userToken)}</div>
            <Container className="d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }} >
                <div className="w-100" style={{ maxWidth: "400px" }}>
                    <Card>
                        <Card.Header as="h5">Log in</Card.Header>
                        <Card.Body>


                            {error && <Alert variant="danger">{error}</Alert>}
                            {success && <Alert variant="success">{success}</Alert>}
                            <Form onSubmit={handleSubmit}>
                                <Form.Group id="email">
                                    <Form.Control type="email" ref={emailRef} placeholder="Email" required />
                                </Form.Group>
                                <Form.Group id="password">
                                    <Form.Control type="password" ref={passwordRef} placeholder="Password" required />
                                </Form.Group>
                                <Button disabled={loading} className="w-100" type="submit">Log In</Button>
                            </Form>
                        </Card.Body>
                    </Card>
                    <Card>
                        <Card.Header as="h5">Sign in with</Card.Header>
                        <Card.Body>

                            <SignInWithGoogle />

                        </Card.Body>
                    </Card>
                    <div className="d-flex align-items-center justify-content-center">
                        Need an account? <Link to="/signup">Sign Up</Link>
                    </div>
                    <div className="d-flex align-items-center justify-content-center">
                        <Link to="/forgot">Forgot password?</Link>
                    </div>
                </div>
            </Container>
        </>
    )
}
