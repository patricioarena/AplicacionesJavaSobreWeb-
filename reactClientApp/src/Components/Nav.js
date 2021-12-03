import React, { useRef, useState } from "react"
import { Link, NavLink } from "react-router-dom";
import LoginControl from './LoginControl'

const Nav = () => {
  return (
    <nav className="main-header navbar navbar-expand-md navbar-light navbar-dark">
      <div className="container">
        <a href="/" className="navbar-brand">
          <img src="../../dist/img/so.png" alt="Subastas online Logo" className="brand-image img-circle elevation-3" style={{ opacity: '.8' }} />
          <span className="brand-text font-weight-light">Subastas online</span>
        </a>

        <button className="navbar-toggler order-1 collapsed" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon" />
        </button>

        <div className="navbar-collapse order-3 collapse" id="navbarCollapse" style={{}}>

          {/* Left navbar links */}
          <ul className="navbar-nav">
            <li className="nav-item">
              <NavLink className="nav-link" id="RouterNavHome" to="/">Home</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" id="RouterNavClientHome" to="/clienthome">Client Home</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" id="RouterNavSellerHome" to="/sellerhome">Seller Home</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" id="RouterNavProduct" to="/product">Product</NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" id="RouterNavAbout" to="/about">About</NavLink>
            </li>
          </ul>

        </div>

        {/* Right navbar links */}
        <div className="navbar-collapse order-3 collapse" id="navbarCollapse" style={{}}>
          <ul className="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
            <li className="nav-item">
              <LoginControl />
            </li>
          </ul>
        </div>
      </div>
    </nav>
  )
}
export default Nav;