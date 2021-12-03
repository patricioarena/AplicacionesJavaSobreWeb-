import React, { useRef, useState } from "react"
import './Ribbon.css';

function Ribbon() {

  const [node_env, setEnv] = useState(process.env.NODE_ENV)
  window.process = node_env

  return (

    <div className="ribbon-wrapper ribbon-xl ribbon-bo2x">
      <div className="ribbon bg-warning text-lg">
      {node_env}
      </div>
    </div>

  )

}

export default Ribbon
