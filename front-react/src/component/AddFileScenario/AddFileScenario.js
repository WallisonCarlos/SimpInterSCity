import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import Constants from '../../util/Constants';

class AddFileScenario extends Component {

    constructor(props) {
        super(props);
        this.onSelectTypeFile = this.onSelectTypeFile.bind(this);
        this.onSelectFile = this.onSelectFile.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    onSelectTypeFile(event) {
        console.log(event.target.value);
        this.props.onSelectTypeFile(event.target.value);
    }

    onSelectFile(event) {
        this.props.onSelectFile(event.target.files[0]);
    }

    handleSubmit(event) {
        this.props.onFileUploadHandler(event);
    }

    render() {
        return (
            <div>
                <hr />
                <h2>Add file to Scenario</h2>
                <Form onSubmit={this.handleSubmit}>
                    <Form.Group controlId="exampleForm.ControlSelect2">
                        <Form.Label>Select file type</Form.Label>
                        <Form.Control as="select" onSelect={this.onSelectTypeFile} onChange={this.onSelectTypeFile}>
                            <option value={Constants.UPLOAD_FILE_TYPE.TRIPS} >Trips</option>
                            <option value={Constants.UPLOAD_FILE_TYPE.MAP} >Map</option>
                            <option value={Constants.UPLOAD_FILE_TYPE.SIGNAL} >Signals</option>
                            <option value={Constants.UPLOAD_FILE_TYPE.METRO}>Metro</option>
                            <option value={Constants.UPLOAD_FILE_TYPE.BUS}>Bus</option>
                            <option value={Constants.UPLOAD_FILE_TYPE.EMPTY_DIGITAL_RAILS}>Empty Digital Rails</option>
                            <option value={Constants.UPLOAD_FILE_TYPE.DIGITAL_RAILS} >Digital Rails</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.File id="exampleFormControlFile1" label="Example file input" name="file" onChange={this.onSelectFile} />
                    </Form.Group>
                    <Button variant="primary" type="submit">
                    Add
                    </Button>
                </Form>
            </div>
        );
    };

};

export default AddFileScenario;