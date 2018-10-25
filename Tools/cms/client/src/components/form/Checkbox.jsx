import React from 'react';
const uuidv4 = require('uuid/v4');

const Checkbox = ({ label, ...restProps }) => {
    const id = uuidv4()
    return (
        <div className="form-group">
            <label>{label}</label>
            <input type="checkbox" className="form-control" {...restProps} />
        </div>
    )
}

export default Checkbox;