package org.uebergebuehr.graphcms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Makes debugging more simple.
 * 
 * @author aschaeffer
 *
 */
public class ColumnLogger {

	/**
	 * The list of columns.
	 */
	private List<Column> columns = new ArrayList<>();

	/**
	 * The string builder.
	 */
	private StringBuilder stringBuilder = new StringBuilder();

	/**
	 * The line separator.
	 */
	private String lineSeparator = "+";

	/**
	 * The row separator.
	 */
	private String rowSeparator = "|";

	/**
	 * The line character.
	 */
	private String lineChar = "-";

	/**
	 * The padding character.
	 */
	private String paddingChar = " ";

	/**
	 * The padding to the left.
	 */
	private Integer paddingLeft = 1;

	/**
	 * The padding to the right.
	 */
	private Integer paddingRight = 1;

	/**
	 * Hidden constructor.
	 */
	private ColumnLogger() {
	}

	/**
	 * Creates a new column logger.
	 * @return The column logger.
	 */
	public static final ColumnLogger create() {
		return new ColumnLogger();
	}

	/**
	 * Sets the line separator.
	 * @param lineSeparator The line separator.
	 * @return The column logger.
	 */
	public final ColumnLogger lineSeparator(final String lineSeparator) {
		this.lineSeparator = lineSeparator;
		return this;
	}

	/**
	 * Sets the row separator.
	 * @param rowSeparator The row separator.
	 * @return The column logger.
	 */
	public final ColumnLogger rowSeparator(final String rowSeparator) {
		this.rowSeparator = rowSeparator;
		return this;
	}

	/**
	 * Sets the line character.
	 * @param lineChar The line character.
	 * @return The column logger.
	 */
	public final ColumnLogger lineChar(final String lineChar) {
		this.lineChar = lineChar;
		return this;
	}

	/**
	 * Sets the padding character.
	 * @param paddingChar The padding character.
	 * @return The column logger.
	 */
	public final ColumnLogger paddingChar(final String paddingChar) {
		this.paddingChar = paddingChar;
		return this;
	}

	/**
	 * Sets the padding size to the left.
	 * @param paddingLeft The padding size to the left.
	 * @return The column logger.
	 */
	public final ColumnLogger paddingLeft(final Integer paddingLeft) {
		this.paddingLeft = paddingLeft;
		return this;
	}

	/**
	 * Sets the padding size to the right.
	 * @param paddingLeft The padding size to the right.
	 * @return The column logger.
	 */
	public final ColumnLogger paddingRight(final Integer paddingRight) {
		this.paddingRight = paddingRight;
		return this;
	}

	/**
	 * Adds a column with the given width and title. The column is left aligned.
	 * @param width The width of the column.
	 * @param title The title of the column.
	 * @return The column logger.
	 */
	public final ColumnLogger addColum(final Integer width, final String title) {
		columns.add(new Column(width, title, false));
		return this;
	}

	/**
	 * Adds a column with the given width and title.
	 * @param width The width of the column.
	 * @param title The title of the column.
	 * @param leftPad If true, the column is right aligned.
	 * @return The column logger.
	 */
	public final ColumnLogger addColum(final Integer width, final String title, final Boolean leftPad) {
		columns.add(new Column(width, title, leftPad));
		return this;
	}

	/**
	 * Adds a row. The N-th parameter is the content of the N-th column.
	 * @param data The N-th parameter is the content of the N-th column.
	 * @return The column logger.
	 */
	public final ColumnLogger addRow(final Object... data) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (Integer i = 0; i < columns.size(); i++) {
			if (data.length > i) {
				Column column = columns.get(i);
				Integer width = column.getWidth();
				String text = StringUtils.abbreviate(StringUtils.trim(data[i].toString()), width);
				if (column.getLeftPad()) {
					sb.append(String.format("%s%s%s%s", rowSeparator, StringUtils.repeat(paddingChar, paddingLeft), StringUtils.leftPad(text, width), StringUtils.repeat(paddingChar, paddingRight)));
				} else {
					sb.append(String.format("%s%s%s%s", rowSeparator, StringUtils.repeat(paddingChar, paddingLeft), StringUtils.rightPad(text, width), StringUtils.repeat(paddingChar, paddingRight)));
				}
			}
		}
		sb.append(rowSeparator);
		stringBuilder.append(sb.toString());
		return this;
	}

	/**
	 * Adds a row which is an horizontal rule.
	 * @return The column logger.
	 */
	public final ColumnLogger hr() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (Integer i = 0; i < columns.size(); i++) {
			Column column = columns.get(i);
			Integer width = column.getWidth() + paddingLeft + paddingRight;
			sb.append(String.format("%s%s", lineSeparator, StringUtils.repeat(lineChar, width)));
		}
		sb.append(lineSeparator);
		stringBuilder.append(sb.toString());
		return this;
	}

	/**
	 * Adds a row with the column titles.
	 * @return The column logger.
	 */
	public final ColumnLogger titles() {
		return addRow(getTitles().toArray());
	}

	/**
	 * Adds the header. This means an horizontal rule, then the column tiles, then another horizontal rule.
	 * @return The column logger.
	 */
	public final ColumnLogger header() {
		return hr()
			.titles()
			.hr();
	}

	/**
	 * Adds the footer. This means an horizontal rule.
	 * @return The column logger.
	 */
	public final ColumnLogger footer() {
		return hr();
	}

	@Override
	public final String toString() {
		return stringBuilder.toString();
	}

	/**
	 * Returns the column titles as list.
	 * @return The column titles as list.
	 */
	private List<String> getTitles() {
		List<String> titles = new ArrayList<>();
		for (Column column : columns) {
			titles.add(column.getTitle());
		}
		return titles;
	}

	/**
	 * Represents a column.
	 * 
	 * @author aschaeffer
	 *
	 */
	class Column {

		/**
		 * The width of the column.
		 */
		private Integer width;

		/**
		 * The title of the column.
		 */
		private String title;

		/**
		 * If true, the column is left padded.
		 */
		private Boolean leftPad;
		
		public Column(final Integer width, final String title, final Boolean leftPad) {
			this.setWidth(width);
			this.setTitle(title);
			this.setLeftPad(leftPad);
		}

		/**
		 * @return the width
		 */
		public final Integer getWidth() {
			return width;
		}

		/**
		 * @param width the width to set
		 */
		public final void setWidth(final Integer width) {
			this.width = width;
		}

		/**
		 * @return the title
		 */
		public final String getTitle() {
			return title;
		}

		/**
		 * @param title the title to set
		 */
		public final void setTitle(final String title) {
			this.title = title;
		}

		/**
		 * @return the leftPad
		 */
		public final Boolean getLeftPad() {
			return leftPad;
		}

		/**
		 * @param leftPad the leftPad to set
		 */
		public final void setLeftPad(final Boolean leftPad) {
			this.leftPad = leftPad;
		}

	}

}
