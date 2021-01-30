import React, { Fragment, Component } from 'react';
import { Navbar,
    NavLink,
    Container,
    Row, 
    Col,
    Button, 
    Form
 } from 'react-bootstrap';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";
import './Home.css';
import ImageWelcome from '../../images/ImageWelcome.png';

class Home extends Component {
    render() {
        return (
            <Fragment>
                <Router>
                    <Navbar>
                        <Navbar.Brand>DISJA</Navbar.Brand>
                        <Navbar.Collapse className="justify-content-end">
                            <NavLink>Masuk Sebagai Pekerja</NavLink>
                            <NavLink>Buat Pekerjaan</NavLink>
                        </Navbar.Collapse>
                    </Navbar>
                    <Switch>
                        {/* <Route path="/PageLogin" component={PageLogin} /> */}
                    </Switch>
                    <Container>
                    <Col sm={6}>
                        <div className="text-center">
                            <h2>Selamat Datang di DISJA</h2>
                            <p className="text-left">Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                        </div>
                        <Row>
                            <div className="searchComponent justify-content-start">
                                <Form>
                                    <Form.Row>
                                        <Col sm={6}>
                                            <input type="text" placeholder="Cari Pekerjaan"/>
                                        </Col>
                                        <Col sm={4}>
                                            <Form.Control as="select" custom placeholder="Lokasi">
                                                <option>Jakarta</option>
                                                <option>Sumatera Utara</option>
                                                <option>Jawa Tengah</option>
                                                <option>Kalimantan Barat</option>
                                                <option>Bali</option>
                                            </Form.Control>
                                        </Col>
                                        <Col>
                                            <Button href="#" variant="outline-primary">Cari</Button>  
                                        </Col>
                                    </Form.Row>
                                </Form>
                            </div>
                        </Row>
                    </Col>
                    <Col sm={6}>
                        <img src={ImageWelcome} className="img-resize" alt="welcome"/>
                    </Col>
                    </Container>
                </Router>
            </Fragment>
        )
    }
}

export default Home;