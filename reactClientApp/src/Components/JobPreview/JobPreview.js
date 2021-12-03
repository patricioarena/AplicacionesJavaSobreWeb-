import { Jumbotron, Container, Button, InputGroup, FormControl } from "react-bootstrap"
import React, { useState, useEffect } from 'react';
import Map from '../Map/Map';


export const JobPreview = (props) => {

    const stateEditorInnerHTML = () => {
        return { __html: `${props.description}` };
    }

    const stateSelectInnerHTML = () => {
        return { __html: `${props.title}` };
    }

    const stateCalendarInnerHTML = () => {
        {
            return (`${props.date.year}/${props.date.month}/${props.date.day}` != "undefined/undefined/undefined")
                ? `${props.date.year}/${props.date.month}/${props.date.day}`
                : ""
        }
    }

    const renderPreview = () => {
        return (
            <>
                <div className="ribbon-wrapper" >
                    <div className="ribbon bg-primary">PREVIEW</div>
                </div>
            </>
        )
    }

    //////////////////////////Refresh////////////////////////////
    // How to re-render after state is changed in react js?
    // https://stackoverflow.com/questions/59408235/how-to-re-render-after-state-is-changed-in-react-js

    // How to manually trigger click event in ReactJS?
    // https://stackoverflow.com/questions/39913863/how-to-manually-trigger-click-event-in-reactjs

    // How can I force a component to re-render with hooks in React?
    // https://stackoverflow.com/questions/53215285/how-can-i-force-a-component-to-re-render-with-hooks-in-react

    // How to rerender component in useEffect Hook
    // https://stackoverflow.com/questions/58015602/how-to-rerender-component-in-useeffect-hook

    const [flag, setFlag] = useState({ 1: false, 2: false });

    useEffect(() => {
        refresh(1); //children function of interest
      }, [props.refresh]);

    const refresh = (id) => {
      var newFlag = Object.assign({}, flag);
      newFlag[id] = !flag[id];
      setFlag(newFlag);
      console.log("after" + JSON.stringify(flag));
      console.log(flag === newFlag);
    };
    /////////////////////////Refresh/////////////////////////////

    // https://react-leaflet.js.org/docs/example-other-layers/
    // https://github.com/smeijer/leaflet-geosearch
    return (
        <>
            <div className="col-md-12" >
                <div className="card" >

                    <div className="card-header">
                        <h3 className="card-title">
                            <i className="fas fa-briefcase" />
                            &nbsp;&nbsp;<a dangerouslySetInnerHTML={stateSelectInnerHTML()} />
                        </h3>
                    </div>

                    <div className="card-body" >
                        <dl className="row">

                            <div className="col-sm-8">
                                <dt className="col-sm-4">Descripción</dt>
                                <dd className="col-sm-12" dangerouslySetInnerHTML={stateEditorInnerHTML()} style={{
                                    'white-space': 'pre-line',
                                    overflowY: 'auto',
                                    maxHeight: '12em'
                                }} />
                                <dt className="col-sm-4">Fecha a realizar</dt>
                                <dd className="col-sm-8">{stateCalendarInnerHTML()}</dd>


                                <div className="card-body">
                                    <blockquote style={{
                                        backgroundColor: '#eaecec69'
                                    }}>
                                        <p>Una vez aceptada la oferta se mostraran datos de contacto y ubicacion especifica del trabajo.</p>
                                    </blockquote>
                                </div>

                            </div>

                            <div className="col-sm-4 f" style={{
                                borderLeft: '1px solid #ced4da',
                            }}>

                                <dl className="row">
                                    <dt className="col-sm-4" style={{}}>Localidad:</dt>
                                    <dd className="col-sm-8" style={{}}>Lorem ipsum dolor </dd>
                                </dl>

                                <div className="col-sm-10"
                                    style={{
                                        display: 'contents'
                                    }}>



                                        {

                                        flag[1]
                                            ? <Map
                                                centerMap={props.centerMap}
                                                activeMarker={false}
                                                activeArea={true}
                                                activeSearch={false}
                                                activeSavePosition={false}
                                                />
                                            : null
                                        }

                                        {
                                        flag[1]
                                            ? null
                                            : <Map
                                                centerMap={props.centerMap}
                                                activeMarker={false}
                                                activeArea={true}
                                                activeSearch={false}
                                                activeSavePosition={false}
                                            />
                                        }



                                </div>

                            </div>

                        </dl>
                    </div>
                    {
                        (props.preview)
                            ? renderPreview()
                            : <></>
                    }
                </div>
            </div>

        </>
    )
}

export default JobPreview;
