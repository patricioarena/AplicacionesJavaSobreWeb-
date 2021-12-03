import React, { useRef, useState } from "react"
import { Container, Form, Button, Card, Alert } from "react-bootstrap"
import { Link } from "react-router-dom"

export default function Forgot() {
    const emailRef = useRef()
    const [error, setError] = useState("")
    const [success, setSuccess] = useState("")
    const [loading, setLoading] = useState(false)


    async function handleSubmit(e) {
        e.preventDefault()

        try {
            setError("")
            setSuccess("")
            setLoading(true)
            // let response = await resetPassword(emailRef.current.value)
            // if (response === true) {
            //     let email = currentUser.email;
            //     setSuccess(`Success reset password!\nBefore we get started, please check your email address. ${email}`)
            // } else {
            //     setError("Failed reset password!")
            // }
        } catch {
            setError("Failed reset password!")
        }
        setLoading(false)

    }

    return (
        <>
            <Container className="d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }} >
                <div className="w-100" style={{ maxWidth: "400px" }}>
                    <Card>
                        <Card.Body>
                            <h2 className="text-center mb-4">Reset Password</h2>
                            {error && <Alert variant="danger">{error}</Alert>}
                            {success && <Alert variant="success">{success}</Alert>}
                            <Form onSubmit={handleSubmit}>
                                <Form.Group id="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" ref={emailRef} required />
                                </Form.Group>
                                <Button disabled={loading} className="w-100" type="submit">Send</Button>
                            </Form>
                        </Card.Body>
                    </Card>
                    <div className="d-flex align-items-center justify-content-center">
                        Need an account? <Link to="/signup">Sign Up</Link>
                    </div>
                    <div className="d-flex align-items-center justify-content-center">
                        Already have an account? <Link to="/login">Log In</Link>
                    </div>
                </div>
            </Container>
        </>
    )
}
