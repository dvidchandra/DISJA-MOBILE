import React, { Component, Fragment } from 'react';
import { Col, 
    Button, 
    Form,
    Container
} from 'react-bootstrap';
import SignIn from './SignIn';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";

class Register extends Component {
    render() {
        return (
            <Fragment>
                <Router>
                    <Container fluid>
                    <Col xs={12} xl={6}>
                        <div className="aside-login">
                            <h1>DISJA</h1>
                            <h2>Selamat Datang Kembali</h2>
                            <p>Untuk tetap terhubung, silahkan masuk dengan data personal Anda</p>
                            <Link to="/SignIn">Masuk</Link>
                        </div>
                    </Col>
                    <Col xs={12} xl={6}>
                        <div className="aside-sign-up">
                            <h2>Buat Akun Baru</h2>
                            <Button href="#" variant="dark">Bergabung dengan akun LinkedIn</Button>
                            <Button href="#" variant="dark">Bergabung dengan akun Gmail</Button>
                            <p>atau gunakan email untuk pendaftaran</p>
                            <Form>
                                <Form.Group>
                                    <Form.Control type="email" placeholder="Masukkan E-Mail Anda"></Form.Control>
                                    <Form.Control type="email" placeholder="Masukkan Password Anda"></Form.Control>
                                    <Button href="#" variant="primary">Daftar</Button>
                                </Form.Group>
                            </Form>
                        </div>
                    </Col>
                    </Container>
                    <Switch>
                        {/* <Route path="/SignIn" component={SignIn} /> */}
                    </Switch>
                </Router>
            </Fragment>
        )
    }
}

export default Register;