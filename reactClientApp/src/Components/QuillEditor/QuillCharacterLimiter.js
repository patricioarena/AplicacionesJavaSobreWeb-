import ReactQuill, {Quill} from "react-quill";

export default class CharacterLimiter {
  constructor(quill, options) {
    this.quill = quill;
    this.options = options;
     quill.on('text-change', function(e) {
      let size = quill.getText();
      if (size.length > options.max + 1)
         quill.history.undo();
   });
  }

}
