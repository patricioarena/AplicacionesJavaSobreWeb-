import React, { useState, useEffect, useRef } from 'react'
import ReactQuill, { Quill } from "react-quill";

import CharacterCounter from "./QuillCharacterCounter";
import CharacterLimiter from "./QuillCharacterLimiter";

import "react-quill/dist/quill.snow.css";
import "./QuillEditor.css";

export const QuillEditor = (props) => {

  Quill.register('modules/characterCounter', CharacterCounter);
  Quill.register('modules/characterLimiter', CharacterLimiter);

  const reactQuillRef = useRef();

 /*
 * Quill modules to attach to editor
 * See https://quilljs.com/docs/modules/ for complete options
 */
  const modules = {
    characterLimiter: {
      max: 500
    },
    characterCounter: {
      container: '#ql-counter',
      unit: 'character'
    },
    toolbar: [
      [{ 'header': '1' }, { 'header': '2' }, { 'font': [] }],
      ['bold', 'italic', 'underline', 'strike', 'blockquote'],
      [{ 'list': 'ordered' }, { 'list': 'bullet' }, { 'indent': '-1' }, { 'indent': '+1' }],
      [{ size: [] }],
      ['clean']
    ],
    clipboard: {
      // toggle to add extra line breaks when pasting HTML:
      matchVisual: false,
    }
  }

  /*
   * Quill editor formats
   * See https://quilljs.com/docs/formats/
   */
  const formats = [
    'header', 'font', 'size',
    'bold', 'italic', 'underline', 'strike', 'blockquote',
    'list', 'bullet', 'indent',
    'link', 'image', 'video'
  ]

  return (
      <>
        <div className="text-editor">
          <ReactQuill
            theme="snow"
            onChange={props.onChange}
            placeholder={props.placeholder}
            value={props.value}
            ref={reactQuillRef}
            modules={modules}
            formats={formats}
          />
        </div>
        <div id="ql-counter" className="ql-counter">0</div>
      </>
    );

};

export default QuillEditor;
