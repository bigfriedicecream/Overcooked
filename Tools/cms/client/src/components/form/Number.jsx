import React from 'react';

const Number = ({ label, ...restProps }) => {
    return (
        <div className="form-group">
            <label>{label}</label>
            <input type="number" className="form-control" {...restProps} />
        </div>
    )
}

export default Number;