import React from 'react'
import { Button, Row } from "react-bootstrap"
import { Link, useHistory } from 'react-router-dom'

import { AuthContext, SignInMethod } from "../Contexts/AuthContext"
import StatesOfApplicacion from '../StatesOfApplication'

const LoginControl = () => {


    const { userToken, removeToken, signInMethod } = AuthContext()

    console.log(userToken);
    console.log('================');
    // console.log(signInMethod);
    console.log(userToken);


    const history = useHistory()


    const renderAvatar = () => {
        return (
            <a href="/myProfile" className="navbar-brand">
            <img src="../../dist/img/user8-128x128.jpg" alt="User picture" className="brand-image img-circle " style={{ opacity: '.8' }} />
            </a>
        )
    }

    const renderSignInDefault = () => {
        return (
            <Link to="/login">
                <Button className="w-100" onClick={() => { }}>Sign In</Button>
            </Link>
        )
    }

    const renderSignOutGoogleButton = () => {
        return (
            <>
                <Row>
                     { renderAvatar() }
                    <Link to="/login">
                        <Button className="w-100" onClick={() => {
                            removeToken();
                            window.localStorage.setItem(StatesOfApplicacion.signOutGoogle2Key, StatesOfApplicacion.signOutGoogle2Value)
                        }}>Sign Out</Button>
                    </Link>
                </Row>

            </>
        )
    }

    const renderSignInGoogleButton = () => {
        return (
            <>
                <Link to="/login">
                    <Button className="w-100" onClick={
                        window.localStorage.setItem(StatesOfApplicacion.signOutGoogle1Key, StatesOfApplicacion.signOutGoogle1Value)
                    }>Sign In</Button>
                </Link>
            </>
        )
    }

    const renderGoogleButtons = () => {
        if (userToken == null) {
            return renderSignInGoogleButton()
        } else {
            return renderSignOutGoogleButton()
        }
    }

    switch (signInMethod) {
        case SignInMethod.Google:
            return renderGoogleButtons();
        default:
            return renderSignInDefault();
    }

}

export default LoginControl;
