import React, { useState } from "react";

const Select = (props) => {

    return (
        <>
            <select className="form-control" onChange={props.onChange}>
                {
                    props.collection.map(item => (<option key={item._id}>{item.jobName}</option>))
                }
            </select>
        </>
    );
};

export default Select;