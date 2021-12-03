import React, { useRef, useState, useEffect } from "react"
import { Button } from "react-bootstrap"
import { AuthContext, SignInMethod } from "../Contexts/AuthContext"
import StatesOfApplicacion from '../StatesOfApplication'

const SignInWithGoogle = () => {

  const { saveToken, removeToken, saveSignInMethod, signInMethod } = AuthContext()
  const [isSignedIn, setSignedIn] = useState(false)

  const auth = useRef(null);

  useEffect(() => {
    window.gapi.load('auth2', () => {
      window.gapi.auth2.init({
        client_id: '436064921337-pdu1pag3mte2vqhkpchimks3np46vp94.apps.googleusercontent.com',
        scope: 'profile email',
      }).then(() => {
        auth.current = window.gapi.auth2.getAuthInstance();
        setSignedIn(auth.current.isSignedIn.get());
        auth.current.isSignedIn.listen(onAuthChange)
      })
    })
  }, [isSignedIn]);

  const onAuthChange = () => {
    setSignedIn(auth.current.isSignedIn.get())

    var token = auth.current.currentUser.get().getAuthResponse().id_token;
    // var profile = auth.current.currentUser.get().getBasicProfile();

    //SignIn
    if (auth.current.isSignedIn.get() == true) {
      saveSignInMethod(SignInMethod.Google)
      saveToken(token)
    }
    else if (isSignedIn == false) {
      //SignOut
      removeToken()
    }
    else {
      //SignOut
      removeToken()
    }
  }

  const onSignOutClick = () => {
    auth.current.signOut()
  }

  const onSignInClick = () => {
    auth.current.signIn()
  }

  // limpiar signingoolge cuando clickean el signout superior estando en la vista login
  if (window.localStorage.getItem(StatesOfApplicacion.signOutGoogle1Key) == StatesOfApplicacion.signOutGoogle1Value) {
    try {
      auth.current.signOut()
      window.localStorage.removeItem(StatesOfApplicacion.signOutGoogle1Key)
    } catch (error) {
    }
    window.localStorage.removeItem(StatesOfApplicacion.signOutGoogle1Key)
  }

  // limpiar signingoolge cuando clickean el signout superior no estando en la vista login
  if (window.localStorage.getItem(StatesOfApplicacion.signOutGoogle2Key) == StatesOfApplicacion.signOutGoogle1Value) {
    try {
      auth.current.signOut()
      window.localStorage.removeItem(StatesOfApplicacion.signOutGoogle2Key)
    } catch (error) {
    }
  }


  const renderSignOutButton = () => {
    return (
      <Button onClick={onSignOutClick} className="w-100">
        Sign Out
        <i className="fas fa-sign-in-alt fa-fw" />
      </Button>
    )
  }

  const renderSignInButton = () => {
    return (
      <Button onClick={onSignInClick} className="w-100">
        <i className="fab fa-google fa-fw" />
        Sign In
      </Button>
    )
  }

  if (isSignedIn === null) {
    return null
  } else if (isSignedIn) {
    return renderSignOutButton()
  } else {
    return renderSignInButton()
  }

}

export default SignInWithGoogle;