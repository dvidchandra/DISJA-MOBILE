import React, { Component, Fragment } from 'react';
import { 
    Button, 
    Container, 
    Form } 
    from 'react-bootstrap';
import {
        BrowserRouter as Router
      } 
    from "react-router-dom";

class SignIn extends Component {
    render() {
        return (
            <Fragment>
                <Router>
                <div className="mx-auto">
                    <Container>
                        <h1>DISJA</h1>
                        <div className="part-login">
                            <Form>
                                <h2>Masuk</h2>
                                <Form.Group>
                                    <Button variant="light">Masuk dengan akun LinkedIn</Button>
                                    <Button variant="light">Masuk dengan akun Google</Button>
                                    <p>atau gunakan email Anda</p>
                                    <Form.Control type="email" placeholder="Email">
                                        Email
                                    </Form.Control>
                                    <Form.Control type="password" placeholder="Email">
                                        Password
                                    </Form.Control>
                                    <Button variant="primary">Masuk</Button>
                                </Form.Group>
                            </Form>
                        </div>
                    </Container>
                </div>
                </Router>
            </Fragment>
        )
    }
}

export default SignIn;