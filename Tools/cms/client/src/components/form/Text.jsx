import React from 'react';

const Text = ({ label, ...restProps }) => {
    return (
        <div className="form-group">
            <label>{label}</label>
            <input type="text" className="form-control" {...restProps} />
        </div>
    )
}

export default Text;