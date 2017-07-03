package kr.ac.ut.eHr.common;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class ImagePaginationRenderer extends AbstractPaginationRenderer {

    public ImagePaginationRenderer() {
        firstPageLabel = "<span class=\"prev_group\"><a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"prev_end\"></a></span>&#160;";
        previousPageLabel = "<span class=\"prev_group\"><a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"prev\"></a></span>&#160;";
        currentPageLabel = "<strong>{0}</strong>&#160;";
        otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
        nextPageLabel = "<span class=\"next_group\"><a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"next\"></a></span>&#160;";
        lastPageLabel = "<span class=\"next_group\"><a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"next_end\"></a></span>&#160;";
    }
}
