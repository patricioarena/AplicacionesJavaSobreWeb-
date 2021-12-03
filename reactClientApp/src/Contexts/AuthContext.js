import React, { useContext, useRef, useState, useEffect } from 'react'
import StatesOfApplicacion from '../StatesOfApplication'

import { UserRole } from "../Repository/UserRole";
import { UserCredentials } from "../Repository/UserCredentials";

export const SignInMethod = {
    EmailAndPassword: 'EmailAndPassword',
    Phone: 'Phone',
    Google: 'Google',
    Facebook: 'Facebook',
    Twitter: 'Twitter',
    GitHub: 'GitHub',
    Yahoo: 'Yahoo',
    Microsoft: 'Microsoft',
    Apple: 'Apple',
    Anonymous: 'Anonymous'
};

const Context = React.createContext()

export function AuthContext() {
    return useContext(Context);
}

export function AuthProvider({ children }) {

    const userMock = {
        "_id": "61762b4324c60000b9b94506",
        "_idRole": "6171ffed7831007cb5105357",
        "name": "Armando",
        "lastName": "Banquiito",
        "birthDate": "2021-12-15T00:11:01.000+00:00",
        "typeDoc": "33688645",
        "numberDoc": 0,
        "gender": "Male",
        "email": "test@gmail.com",
        "telephoneNumber": "1133112244",
        "reputation": 10,
        "address": {
          "street": "Madrid",
          "number": "432",
          "zipCode": "1888",
          "city": "Florencio Varela",
          "provState": "Buenos Aires",
          "country": "Argentina"
        },
        "deleted": false
      };

    const [userToken, setUserToken] = useState(null)
    const [signInMethod, setSignInMethod] = useState(null)
    const [userPerfil, setUserPerfil] = useState(null)


    useEffect(() => {
        setUserPerfil(userMock)
        try {
            var userTokenInLocalStorage = window.localStorage.getItem(StatesOfApplicacion.userToken)
            var userSignInMethod = window.localStorage.getItem(StatesOfApplicacion.signInMethod)

            setSignInMethod(JSON.parse(userSignInMethod))
            setUserToken(JSON.parse(userTokenInLocalStorage))
        } catch (error) {
            console.error(error);
        }
    }, [])

    const saveToken = (token) => {
        setUserToken(token);
        window.localStorage.setItem(StatesOfApplicacion.userToken, JSON.stringify(token))
    }

    const removeToken = () => {
        setUserToken(null);
        window.localStorage.removeItem(StatesOfApplicacion.userToken)
        window.localStorage.removeItem(StatesOfApplicacion.signInMethod)
        window.localStorage.removeItem(StatesOfApplicacion.signOutGoogle1Key)

    }

    const saveSignInMethod = (signInMethod) => {
        setSignInMethod(signInMethod);
        window.localStorage.setItem(StatesOfApplicacion.signInMethod, JSON.stringify(signInMethod))
    }

    const value = {
        userPerfil,
        userToken,
        signInMethod,
        saveToken,
        removeToken,
        saveSignInMethod
    }

    return (
        <Context.Provider value={value}>
            {children}
        </Context.Provider>
    )
}
