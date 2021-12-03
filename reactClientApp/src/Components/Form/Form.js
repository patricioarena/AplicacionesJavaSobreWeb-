import React, { useState, useEffect, useCallback, useRef, useImperativeHandle } from 'react';
import { Jumbotron, Container, Button, InputGroup, FormControl } from "react-bootstrap"

import { AuthContext } from "../../Contexts/AuthContext"

import QuillEditor from '../QuillEditor/QuillEditor';
import JobPreview from '../JobPreview/JobPreview';
import Calendar from '../Calendar/Calendar';
import Select from '../Select/Select';
import Map from '../Map/Map';

import  Requirement from '../Models/Requirement';
import  Address from '../Models/Address';


export const Form = () => {

    const centerMap = [51.505, -0.09]
    const text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </br> Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    const worklistsMock = [
        {
            "_id": "6164c4289242d360123a0f78",
            "jobName": "Pintor",
            "deleted": false
        },
        {
            "_id": "6164c64bf6733464c61bdc67",
            "jobName": "Gasista",
            "deleted": false
        }
    ];

    const requientsStatus = [
        {
          "_id": "618f0e28b5658f18666def83",
          "status": "Open"
        },
        {
          "_id": "618f0e35b5658f18666def84",
          "status": "Close"
        },
        {
          "_id": "618f29edf8f454434147f2b3",
          "status": "Canceled"
        }
      ];


    const [stateMap, setStateMap] = useState(centerMap)
    const [stateSelected, setStateSelected] = useState("");
    const [stateCalendar, setStateCalendar] = useState("");
    const [stateEditorText, setStateEditorText] = useState(text);
    const [worklists, setStateWorklists] = useState(worklistsMock);

    const [street, setStreet] = useState("Calle");
    const [number, setNumber] = useState("Numeración");
    const [zipCode, setZipCode,] = useState("Codigo Postal");
    const [city, setCity] = useState("Ciudad");
    const [provState, setProvState] = useState("Provincia");
    const [country, setCountry] = useState("Pais");

    const { userPerfil } = AuthContext()

    const handleChangeSelect = (event) => {
        {
            (event != "Seleción")
                ? setStateSelected(event)
                : setStateSelected("")
        }
    };

    const handleChangeEditorText = (event) => {
        setStateEditorText(event);
    };

    const handleChangeCalendar = (event) => {
        setStateCalendar(event);
    };

    const btnRef = useRef(); // Refresh 1 componente (mapPreview in JabPreview)
    const btnRef2 = useRef(); // Refresh 1 componente (mapPreview in JabPreview)
    const [refresh, doRefresh] = useState(0); // Refresh 1 componente (mapPreview in JabPreview)

    const handleSaveMapCoordinate = (event) => {
        setStateMap(event);
        btnRef.current?.click();
    };

    useEffect(() => {

        // setStreet(userPerfil.address.street);
        // setNumber(userPerfil.address.number);
        // setZipCode(userPerfil.address.zipCode);
        // setCity(userPerfil.address.city);
        // setProvState(userPerfil.address.provState);
        // setCountry(userPerfil.address.country);

        try {
            getWorkLists();
        } catch (error) {
            console.log(error);
            console.log("se cargo el worklist por defector");
        }

    }, [])

    const getWorkLists = async () => {
        const url = "http://localhost:8090/api/typeJob/getAll";
        const response = await fetch(url, {
            // headers: {Authentication: `Bearer ${userToken}`}
        });

        const jsonresponse = await response.json();
        // console.log(jsonresponse.data);
        setStateWorklists(jsonresponse.data)
    }

    const setRequirements = async () => {
        var jobId = worklists.filter(job => job.jobName == stateSelected);
        let coordinates;

        var temp = new Requirement();
        temp._idRequestPerson = "617638887e4fff79d6ec72a8"
        temp._idTypeJob = jobId[0]._id
        temp._idRequirementStatus = "618f0e28b5658f18666def83"
        temp.address = new Address(
            street,
            number,
            zipCode,
            city,
            provState,
            country,
            coordinates = {
                lat:  stateMap[0],
                long: stateMap[1]
            }
        )
        temp.date = `${stateCalendar.year}-${stateCalendar.month}-${stateCalendar.day}`
        temp.description= stateEditorText
        temp.zone = zipCode

        const url = "http://localhost:8090/api/requirement/create";
        const response = await fetch(url, {
            // headers: {Authentication: `Bearer ${userToken}`}
            method: "POST",
            body: JSON.stringify(temp),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        });

        const jsonresponse = await response.json();
        console.log(jsonresponse.data);

        // console.log(stateSelected)
        // console.log(stateCalendar)
        // console.log(stateEditorText)
        // console.log(street)
        // console.log(number)
        // console.log(zipCode)
        // console.log(city)
        // console.log(provState)
        // console.log(country)
        // console.log(coordinates);
        // console.log(temp);

    }


    return (
        <>
            <div className="row">

                <div className="col-md">
                    <div className="card card-warning">
                        <div className="card-header d-flex justify-content-between align-items-center">
                            <div className="col-sm-11">
                                <h3 className="card-title">
                                    <i className="fas fa-edit" />
                                    &nbsp;&nbsp;<a>Crear publicación</a>
                                </h3>

                            </div>
                            <div className="col">
                                <Button onClick={event => setRequirements()}> Publicar </Button>
                            </div>
                        </div>

                        <div className="card-body">
                            <div>

                                <div className="row">

                                    <div className="col-sm-6">

                                        <div className="row">
                                            <div className="col-sm-4">
                                                <label>Trabajo a realizar</label>
                                                <div className="form-group">
                                                    <Select
                                                        onChange={event => handleChangeSelect(event.target.value)}
                                                        collection={worklists} />
                                                </div>
                                            </div>

                                            <div className="col-sm-4"  >
                                                <label>Fecha a realizar</label>
                                                <div className="form-group">
                                                    <Calendar
                                                        onChange={event => handleChangeCalendar(event)}
                                                        value={stateCalendar} />
                                                </div>
                                            </div>
                                        </div>


                                        <>
                                            <div className="row">
                                                <div className="col-sm-4">
                                                    <div className="form-group">
                                                        <label>Calle</label>
                                                        <FormControl
                                                            placeholder="Calle"
                                                            aria-label="Calle"
                                                            defaultValue={street}
                                                        />
                                                    </div>
                                                </div>

                                                <div className="col-sm-4">
                                                    <div className="form-group">
                                                        <label>Numeración</label>
                                                        <FormControl
                                                            placeholder="Numeración"
                                                            aria-label="Numeración"
                                                            defaultValue={number}
                                                        />
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="row">
                                                <div className="col-sm-4">
                                                    <div className="form-group">
                                                        <label>Codigo Postal</label>
                                                        <FormControl
                                                            placeholder="Codigo Postal"
                                                            aria-label="Codigo Postal"
                                                            defaultValue={zipCode}

                                                        />
                                                    </div>
                                                </div>

                                                <div className="col-sm-4">
                                                    <div className="form-group">
                                                        <label>Ciudad</label>
                                                        <FormControl
                                                            placeholder="Ciudad"
                                                            aria-label="Ciudad"
                                                            defaultValue={city}
                                                        />
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="row">
                                                <div className="col-sm-4">
                                                    <div className="form-group">
                                                        <label>Provincia</label>
                                                        <FormControl
                                                            placeholder="Provincia"
                                                            aria-label="Provincia"
                                                            defaultValue={provState}
                                                        />
                                                    </div>
                                                </div>

                                                <div className="col-sm-4">
                                                    <div className="form-group">
                                                        <label>Pais</label>
                                                        <FormControl
                                                            readOnly
                                                            placeholder="Pais"
                                                            aria-label="Pais"
                                                            // defaultValue={country}
                                                            defaultValue={country}
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </>


                                    </div>

                                    <div className="col-sm-6">
                                        <div className="card-body">
                                            <Container style={{
                                                width: '30em',
                                                height: '19em'
                                            }}>

                                                <Map
                                                    centerMap={stateMap}
                                                    activeMarker={true}
                                                    activeArea={true}
                                                    activeSearch={true}
                                                    activeSavePosition={true}
                                                    style={{ 'width': '8em' }}
                                                    saveCoordinate={handleSaveMapCoordinate} // Con esta propiedad se puede setear desde el boton Save Map el centro a un estado externo al componente Map
                                                />

                                            </Container>
                                        </div>
                                    </div>
                                </div>


                                <div className="row">
                                    <div className="col-sm-12">
                                        <div className="form-group">
                                            <QuillEditor
                                                onChange={event => handleChangeEditorText(event)}
                                                placeholder={"Descripción del trabajo"}
                                                value={stateEditorText} />
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>




            </div>
            <div className="row">
                {/* <RefreshExample  refresh={refresh} /> */}
                <Button onClick={()=>doRefresh(refresh+1)}  ref={btnRef} style={{display: "none"}}>Refresh</Button>
                <JobPreview description={stateEditorText} title={stateSelected} date={stateCalendar} preview={true} centerMap={stateMap}
                    refresh={refresh}
                />


            </div>

        </>
    )
}


export default Form;