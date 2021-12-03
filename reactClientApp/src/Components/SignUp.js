import React, { useRef, useState } from "react"
import { Container, Form, Button, Card, Alert } from "react-bootstrap"
import { Link } from "react-router-dom"

const Signup = () => {
    const emailRef = useRef()
    const passwordRef = useRef()
    const passwordConfirmRef = useRef()
    const [error, setError] = useState("")
    const [success, setSuccess] = useState("")
    const [loading, setLoading] = useState(false)

    async function handleSubmit(e) {
        e.preventDefault()

        if (passwordRef.current.value !== passwordConfirmRef.current.value) {
            return setError("Passwords do not match")
        }

        setError("")
        setSuccess("")
        setLoading(true)
        // let response = await signup(emailRef.current.value, passwordRef.current.value)
        // if (response === true) {
        //     let email = window.localStorage.getItem('email');
        //     setSuccess(`Success to create account!\nBefore we get started, please confirm your email address.\n${email}`)
        //     sendEmailVerification()
        // } else {

        //     setError("Failed to create account!")
        // }

        setLoading(false)
    }

    return (
        <>
            <Container className="d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }} >
                <div className="w-100" style={{ maxWidth: "400px" }}>
                    <Card>
                        <Card.Header as="h5">Sign Up</Card.Header>
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
                                <Form.Group id="password-confirm">
                                    <Form.Control type="password" ref={passwordConfirmRef} placeholder="Password Confirmation" required />
                                </Form.Group>
                                <Button disabled={loading} className="w-100" type="submit">Sign Up</Button>
                            </Form>
                        </Card.Body>
                    </Card>
                    <div className="d-flex align-items-center justify-content-center">
                        Already have an account? <Link to="/login">Log In</Link>
                    </div>
                    <div className="d-flex align-items-center justify-content-center">
                        <Link to="/forgot">Forgot password?</Link>
                    </div>
                </div>
            </Container>
        </>
    )
}
export default Signup;